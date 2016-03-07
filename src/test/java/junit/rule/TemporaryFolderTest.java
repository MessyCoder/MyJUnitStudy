package junit.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class TemporaryFolderTest {

    /**
     * TemporaryFolder 会保证在测试期间通过该对象创建文件都被删除。（无论成功与否）
     */
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFile = temporaryFolder.newFile("myfile.txt");
        File createdFolder = temporaryFolder.newFolder("mysubfolder");
    }
}
