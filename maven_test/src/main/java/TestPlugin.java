import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Created by funny
 * Date: 2021/11/4
 * Time: 19:32
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
@Mojo(name = "test")
public class TestPlugin extends AbstractMojo {

    @Parameter(defaultValue = "${project.basedir}", property = "baseDir", required = true)
    private File baseDir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println(baseDir);
    }

    public static void main(String[] args) throws MojoExecutionException, MojoFailureException {
        new TestPlugin().execute();
    }
}
