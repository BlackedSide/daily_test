import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by funny
 * Date: 2021/11/4
 * Time: 19:50
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
public class GitTest {
    @Test
    public void config() throws IOException {
        String path = "/Users/funny/Qunar/Github/daily_test";
        String path2 = "/Users/funny/Qunar/Basic_Platform/spider";
        File file = new File(path);
        StoredConfig config = Git.open(file).getRepository().getConfig();
        Repository repository = Git.open(file).getRepository();
        System.out.println(repository.getDirectory().getParent());
        System.out.println(config.getString("user", null, "user"));
    }
}
