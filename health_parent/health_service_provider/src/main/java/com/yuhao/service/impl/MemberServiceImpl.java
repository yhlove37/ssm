package com.yuhao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yuhao.dao.MemberDao;
import com.yuhao.pojo.Member;
import com.yuhao.service.MemberService;
import com.yuhao.utils.DateUtils;
import com.yuhao.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for (String m : month) {
            try {
                String date_str = m + ".1";
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                calendar.setTime(sdf.parse(date_str));
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                // 获取当月最后一天
                String lastDayOfMonth = sdf.format(calendar.getTime());
                Integer count = memberDao.findMemberCountBeforeDate(lastDayOfMonth);
                list.add(count);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
