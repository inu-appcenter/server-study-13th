package inu.appcenter.jaekwon.model.member;

import lombok.Data;

@Data
public class MemberSaveRequest {

    private String email;
    private int age;
    private String name;
}
