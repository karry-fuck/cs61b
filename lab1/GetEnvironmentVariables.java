import java.util.Properties;

public class GetEnvironmentVariables {

    //  Fill in the path to your sp21-s*** folder between the quotes
    public static String REPO_DIR = "/home/killer_root/cs61b";

    //  Fill in the path to your snaps-sp21-s*** folder between the quotes
    public static String SNAPS_DIR = "/home/killer_root/cs61b";

    // Fill in the type of your shell by running 'echo $0` in your terminal. It should be zsh or bash.
    public static String SHELL = "zsh";
    public static void main(String[] args) {
        String zshConfig = "echo 'export {variable}={value}' >> ~/.zshrc"; // Zsh 的配置文件是 .zshrc
        String bashConfig = "echo 'export {variable}={value}' >> ~/.bashrc";
        String zshSource = "source ~/.zshrc";
        String bashSource = "source ~/.bashrc";

        String yourOS = System.getProperty("os.name").toLowerCase();

        String repo = null;
        String snaps = null;
        String source = null;
        if (yourOS.contains("nux")) { // 检测到 Linux（Ubuntu）
            if (SHELL.equals("zsh")) {
                repo = zshConfig.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
                snaps = zshConfig.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
                source = zshSource;
            } else { // 如果仍用 bash
                repo = bashConfig.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
                snaps = bashConfig.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
                source = bashSource;
            }
        }

        if (repo == null) {
            System.out.println("错误：不支持的操作系统。");
            return;
        }

        System.out.println("请执行以下命令：");
        System.out.println(repo);
        System.out.println(snaps);
        System.out.println(source);
    }
}