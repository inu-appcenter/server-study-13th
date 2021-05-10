package com.example.hw2.model.member.response;

import com.example.hw2.domain.Member;
import com.example.hw2.model.todo.response.ToDoDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {

    private Long id;
    private String email;
    private int age;
    private String name;

    private List<ToDoDTO> todoList;

    public MemberResponse(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();

        this.todoList = member.getTodoList().stream().map(ToDoDTO::new).collect(Collectors.toList());
    }

}
