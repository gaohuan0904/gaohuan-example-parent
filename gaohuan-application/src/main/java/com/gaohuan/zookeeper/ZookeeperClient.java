package com.gaohuan.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * 练习使用
 */
public class ZookeeperClient {
    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zooKeeper = null;
        Stat stat = null;
        try {
            zooKeeper = new ZooKeeper("localhost:2181", 3000, (WatchedEvent event) -> System.out.println("触发事件:" + event.getType()));
            if (!exists(zooKeeper, "/zkRoot")) {
                zooKeeper.create("/zkRoot", "root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            if (!exists(zooKeeper, "/zkRoot/node1")) {
                zooKeeper.create("/zkRoot/node1", "/zkRoot/node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String data = new String(zooKeeper.getData("/zkRoot", false, null));
            System.out.println("/zkRoot:" + data);
            data = new String(zooKeeper.getData("/zkRoot/node1", false, null));
            System.out.println("/zkRoot/node1:" + data);
            zooKeeper.setData("/zkRoot/node1", "modify".getBytes(), -1);
            stat = zooKeeper.exists("/zkRoot", true);
            System.out.println("目录状态:" + stat);
            if (!exists(zooKeeper, "/zkRoot/node2")) {
                zooKeeper.create("/zkRoot/node2", "/zkRoot/node2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            System.out.println(new String(zooKeeper.getData("/zkRoot/node2", true, null)));
            //删除节点
            zooKeeper.delete("/zkRoot/node1", -1);
            zooKeeper.delete("/zkRoot/node2", -1);
            zooKeeper.delete("/zkRoot", -1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }
    }

    public static boolean exists(ZooKeeper zooKeeper, String nodePath) throws KeeperException, InterruptedException {
        return (null != zooKeeper.exists(nodePath, true)) ? true : false;


    }
}
