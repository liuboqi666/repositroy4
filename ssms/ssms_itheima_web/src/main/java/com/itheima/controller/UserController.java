package com.itheima.controller;

import com.itheima.domain.Pages;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<User> users = userService.findAll();
         model.addAttribute("userList", users);
       return "list";
    }
    @RequestMapping("/login")
    public String login(User user,String ck, HttpSession session, HttpServletResponse response){
        User user1 = userService.findUser(user);



        if (user1!=null){


            Cookie cookieNanme = new Cookie("username",user.getUsername());
            Cookie cookiePwd = new Cookie("pwd",user.getPwd());
            /*设置cookie是客户端绘画技术
             * * 一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止*/
            System.out.println(ck);
            if ("1".equals(ck)){
                cookieNanme.setMaxAge(60*60*24*7);
                cookiePwd.setMaxAge(60*60*24*7);
                cookieNanme.setPath("/");
                cookiePwd.setPath("/");
            }else {
                cookieNanme.setMaxAge(0);
                cookiePwd.setMaxAge(0);
                cookieNanme.setPath("/");
                cookiePwd.setPath("/");
            }

            /*将cookie响应到页面上*/
            response.addCookie(cookieNanme);
            response.addCookie(cookiePwd);

            session.setAttribute("user",user1);
            return "index1";
        }
        return "loginError";
    }
  @RequestMapping("/save")
    public String save(User user){
     if (user!=null&&user.getUsername().length()>0){
         User user1 = userService.findOne(user);
         if (user1==null){
             userService.saveUser(user);
             return "redirect:/user/pages";
         }

     }
     return "error";
    }
   @RequestMapping("/delete")
    public String delete(User user,HttpSession session){
       User user1 = (User) session.getAttribute("user");
       if (!(user.getUsername().equals(user1.getUsername()))){
           userService.delete(user);
       }

        return "redirect:/user/findAll";
    }
    @RequestMapping("findOne")
    public  String findOne(User user,Model model){
       User u=   userService.findOne(user);
        System.out.println(u);
        model.addAttribute("user",u);
       return "update";
    }
    /*点击list页面的修改按钮，并且专递参数 ，查询数据*/
    @RequestMapping("/update")
    public  String update(User user, HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        user.setId(Integer.parseInt(id));
        userService.update(user);

        return "redirect:/user/pages";
    }
    @RequestMapping("/pages")
    public  String pages(Pages page,Model model){
        System.out.println(page);
        if (page.getPageNum()==null){
            page.setPageNum(1);
        }
        if (page.getPageCounts()==null){
            page.setPageCounts(3);
        }
        /*总的记录数*/

      Integer counts= userService.findCount(page);
        System.out.println(counts);

      page.setCounts(counts);
        /*每页显示条数*/
        Integer pageCounts = page.getPageCounts();
         /*总的页数*/
        int pages= counts % pageCounts ==0 ? counts /pageCounts :(counts/pageCounts)+1;
          page.setPages(pages);

        Integer  startNum=(page.getPageNum()-1)*pageCounts;


        page.setStartNum(startNum);
       List<User>  userList= userService.findList(page);

       model.addAttribute("pages",page);
       model.addAttribute("userList",userList);
       return "list";
    }
    @RequestMapping("/delSelect")
    public  String delSelect(HttpServletRequest request){
        String[] uids = request.getParameterValues("uid");
        System.out.println("--------------");
        System.out.println(uids.length);
        if (uids!=null && uids.length>0){
            for (String id : uids) {
                userService.delSelect(id);
            }

        }
        return "redirect:/user/pages";
    }
}
