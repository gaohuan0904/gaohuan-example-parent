import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

String path = "D:\\idea-work\\gaohuan-example-parent\\gaohuan-application\\src\\main\\java\\com\\gaohuan\\java8\\GroovyMain.groovy";
String toFile = "D:\\idea-work\\gaohuan-example-parent\\gaohuan-application\\src\\main\\java\\com\\gaohuan\\java8\\toFile.txt";
/*
List<String> lines = Files.readAllLines(Paths.get(path));

//说明：groovy中lambda表达式要用{}包起来使用
Consumer consumer={s->println(s)};
lines.forEach(consumer);

*/
/*
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
*/
FileChannel fileChannel;
try {
    fileChannel = Files.newByteChannel(Paths.get(toFile), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    ByteBuffer buffer = ByteBuffer.allocate(26);
    for (int i = 0; i < 26; i++) {
        int b = (int) 'A' + i;
        buffer.put((byte) b);
    }
    buffer.rewind();
    fileChannel.write(buffer);
} finally {
    if (fileChannel != null) {
        fileChannel.close();
    }
}

