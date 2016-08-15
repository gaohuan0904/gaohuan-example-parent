import java.nio.ByteBuffer
import java.nio.channels.SeekableByteChannel
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.FileAttribute
import java.util.concurrent.Callable
import java.util.function.Consumer

String path = "D:\\idea-work\\gaohuan-example-parent\\gaohuan-application\\src\\main\\java\\com\\gaohuan\\java8\\GroovyMain.groovy";
/*
List<String> lines = Files.readAllLines(Paths.get(path));

//说明：groovy中lambda表达式要用{}包起来使用
Consumer consumer={s->println(s)};
lines.forEach(consumer);

*/
try {
    int count;
    SeekableByteChannel fChan = Files.newByteChannel(Paths.get(path));
    ByteBuffer buffer = ByteBuffer.allocate(128);
    while ((count = fChan.read(buffer)) != -1) {
        buffer.rewind()
        String str = new String(buffer.array(), Charset.forName("utf-8"))
        print str;
    }

} finally {
    fChan.close();
}
