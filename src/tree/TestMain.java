package tree;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by tiang on 2017/10/18.
 * 测试树是否正确
 */
public class TestMain {
    public static void main(String[] args) {
        Tree<Account, Team> tree = new Tree<>();
        Account[] accounts = new Account[]{
                new Account("3", "小新"),
                new Account("3", "朝辉"),
                new Account("3", "空"),
                new Account("3", "小仙女")
        };
        Team[] teams = new Team[]{
                new Team("0", "", "root"),
                new Team("1", "0", "HRBD销售团队@自动化"),
                new Team("2", "0", "HR BD部门"),
                new Team("3", "2", "HR BD一组")
        };
        teams[3].filter = list -> list.stream().filter(t->!t.name.equals("空")).collect(Collectors.toList());
        tree.makeTree(Arrays.asList(teams), Arrays.asList(accounts));
        tree.printTree();
    }
}

class Account extends TreeLeaf {
    String name;
    Account(String id, String name){
        this.id = id;
        this.name = name;
    }
    public String toString(){
        return name;
    }
}

class Team extends TreeNode<Account>{
    Team(String id, String pid, String name){
        this.ID = id;
        this.parentId = pid;
        this.nodeName = name;
    }
}