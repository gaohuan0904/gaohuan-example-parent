package com.gaohuan.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.aop.DefaultAnnotationResolver;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.util.Arrays;

/**
 * Created by acer on 2016/7/25.
 */
public class C2Test {

    @After
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

    protected void login(String iniFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

    }

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-all-success.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini", "wang", "123");
        Subject subject = SecurityUtils.getSubject();
    }

    @Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.hasRole("role1"));
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] results = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, results[0]);
        Assert.assertEquals(true, results[1]);
        Assert.assertEquals(false, results[2]);

        subject.checkRole("role1");
        subject.checkRoles("role1", "role3");
    }

    @Test
    public void testPermitted() {
        login("classpath:shiro-role.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        subject.isPermitted("user:create");
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
    }

    @Test
    public void testNonConfigurationCreate() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);

        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("gaohuan0904");

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        securityManager.setRealms(Arrays.asList(jdbcRealm));

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }

    @Test
    public void testHas() {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt).toString();
        System.out.println(md5);

        String sha1 = new Sha256Hash(str, salt).toBase64();
        System.out.println(sha1);

        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);
        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123"))
                .setIterations(2)
                .build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);


        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        String encryptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        System.out.println(encryptText);
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encryptText), key.getEncoded()).getBytes());
        Assert.assertEquals(text, text2);

    }

    @Test
    public void testRetryLimitHashMatcher() {
        for (int i = 0; i < 5; i++) {
            try {
                login("classpath:shiro-retrylimithashmatcher.ini", "zhang", "1234");
            } catch (Exception e) {

            }
        }
        login("classpath:shiro-retrylimithashmatcher.ini", "zhang", "1234");

    }


}
