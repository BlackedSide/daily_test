import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
        String path = "/Users/fanny/Qunar/Github/daily_test";
        String path2 = "/Users/fanny/Qunar/Basic_Platform/spider";
        File file = new File(path);
        StoredConfig config = Git.open(file).getRepository().getConfig();
        Repository repository = Git.open(file).getRepository();
        System.out.println(repository.getDirectory().getParent());
        System.out.println(config.getString("user", null, "user"));
    }

    @Test
    public void resolveAll() throws IOException {
        File file = new File("/Users/fanny/Qunar/Basic_Platform/h_n_callcenter/callcenter-flow/flow-dao");
        File rootFile = getProjectRoot(file, 0);
        System.out.println(rootFile);
        recurse(rootFile, 0);
    }

    private static void recurse(File rootFile, int counting) {
        if (counting >= recurseDepth + 1) {
            return;
        }
        if (rootFile.exists() && rootFile.isDirectory()) {
            ++counting;
            for (File subModule : Objects.requireNonNull(rootFile.listFiles())) {
                if (subModule.getName().equals("target") && subModule.isDirectory()) {
                    for (File output : Objects.requireNonNull(subModule.listFiles())) {
                        if (output.getName().equals("classes") && output.isDirectory()) {
                            System.out.println(rootFile.getName() + "->" + subModule.getName() + "->" + output.getName());
                        }
                    }
                } else {
                    recurse(subModule, counting);
                }
            }
        }
    }

    private final static int recurseDepth = 5;

    private static File getProjectRoot(File baseDir, int count) {
        try {
            ++count;
            String rootPath = Git.open(baseDir).getRepository().getDirectory().getParent();
            return new File(rootPath);
        } catch (Exception e) {
            if (count >= recurseDepth) {
                throw new RuntimeException("无法解析Git Repository根路径");
            }
            return getProjectRoot(baseDir.getParentFile(), count);
        }
    }
}
