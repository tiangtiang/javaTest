package leetcode;

import java.util.LinkedList;

/**
 * Created by lianglab on 2018/3/23.
 */
public class SimplifyPath {
    /**
     * Given an absolute path for a file (Unix-style), simplify it.
     For example,
     path = "/home/", => "/home"
     path = "/a/./b/../../c/", => "/c"

     采用数据结构 栈和队列，先采用栈，剔除不必要的路径，再采用队列，从头读取有用的路径
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        LinkedList<String> usefulPath = new LinkedList<>();
        for(String p : strs){
            if(!p.equals("")){
                if(p.equals("."))
                    continue;
                else if(p.equals("..")){
//                    if(usefulPath.size() == 0||usefulPath.peek().equals("..")){
//                        usefulPath.push(p);
//                    }else
                    if(usefulPath.size()>0)
                        usefulPath.pop();
                }else{
                    usefulPath.push(p);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(usefulPath.size() == 0)
            return "/";
        while(usefulPath.size()>0){
            sb.append('/').append(usefulPath.pollLast());
        }
        return sb.toString();
    }
}
