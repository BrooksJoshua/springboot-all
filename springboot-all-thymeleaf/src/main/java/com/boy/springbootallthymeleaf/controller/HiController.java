package com.boy.springbootallthymeleaf.controller;

import com.boy.springbootallthymeleaf.entity.Student;
import com.boy.springbootallthymeleaf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-22 13:35
 */
@Controller
public class HiController {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi(Model model){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i,"Joshua"+i,"Brooks"+i));
        }
        model.addAttribute("users",users);
        return "hi";
    }

    @RequestMapping(value = "/mail",method = RequestMethod.GET)
    public String mail(Model model, HttpSession session){
        session.setAttribute("name","Joshua.H.Brooks");
        //集合
        ArrayList<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User(i,"Joshua"+i,"Brooks"+i));
        }
        model.addAttribute("userList",userList);
        //数组
        User[] userArray =  new User[10];
        for (int i = 0; i < userArray.length; i++) {
            userArray[i] = new User(i,"Elo"+i,"Pra"+i);
        }
        model.addAttribute("userArray",userArray);
        return "mail/AdmissionNotice";
    }

    @RequestMapping(value = "/date",method = RequestMethod.GET)
    public String formatDate(Model model){
        //日期
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.add(Calendar.DATE, 1);
        cal2.add(Calendar.DATE, 2);

        Date[] arr = new Date[]{cal1.getTime(), cal2.getTime()};
        List list = Arrays.asList(arr);
        Set set = new HashSet(list);

        model.addAttribute("date", date);
        model.addAttribute("cal", cal);
        model.addAttribute("arr", arr);
        model.addAttribute("list", list);
        model.addAttribute("set", set);
        return "date";
    }

    @RequestMapping(value = "/messages",method = RequestMethod.GET)
    public String messages(Model model){
        model.addAttribute("messageKeyList","myMessageKeyList");
        return "messages";
    }

    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public String students(Model model){

        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i+"", "Student","天河"+i+"路"));
        }
        model.addAttribute("students",students);
        return "traverse";
    }
    @RequestMapping(value = "/ic",method = RequestMethod.GET)
    public String ic(Model model){
        return "innerCorrelation";
    }


}

