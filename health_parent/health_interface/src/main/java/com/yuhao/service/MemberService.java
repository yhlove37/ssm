package com.yuhao.service;

import com.yuhao.pojo.Member;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);

    List<Integer> findMemberCountByMonth(List<String> month);
}
