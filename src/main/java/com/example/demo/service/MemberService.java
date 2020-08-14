package com.example.demo.service;

import com.example.demo.entity.Member;

import java.util.List;

public interface MemberService {
    public void register(Member member) throws Exception;
    public void setupAdmin(Member member) throws Exception;

    public Member read(Long userNo) throws Exception;
    public void modify(Member member) throws Exception;
    public void remove(Long userNo) throws Exception;
    public List<Member> list() throws Exception;
    public long countAll() throws Exception;
}
// regiser
// setup 처음에 관리자계정만들어줘야함 -> 성격이 레지스터랑 똑같음-> 멤버를 가져와야하니까 member
// read ->  읽어야하니까  userNo을 가져오고  Long임  ->  member의 userNo을 보여줘야하니까 void 말고 member
// modify -> 수정해야하니까 member -> 얘는 ?????왜 void?
// remove -> 번호에 해당하는걸 지우는 거니까 long 지우고 끝이니까 void
// list 리스트를 가져오는거니까 리스트로 바꿈
// count 개수를 새는거니까 void를 long으로 바꿔주기