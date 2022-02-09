package com.sinc.goodmd.oassis.member;

import com.sinc.goodmd.oassis.config.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemeberController {

    @Autowired
    private MemberService memberService;

    private final String LogMarker = "MemberController::";

    @PostMapping("/join")
    public CommonResponse joinMember(@RequestBody Member memeber){

        log.debug(LogMarker, memeber.toString());
        CommonResponse response = new CommonResponse();

        int cnt = memberService.save(memeber);
        response.setRtnCnt(cnt);

        if(cnt == 0){
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setRtnMsg("Insert Failed");
        }

        response.setStatusCode(HttpStatus.OK);
        response.setRtnMsg("Insert Success");

        return response;
    }

    @GetMapping("/{userId}")
    public CommonResponse getMember(@PathVariable String userId){

        log.debug(LogMarker, userId);
        CommonResponse response = new CommonResponse();

        Member getMember = memberService.findByUserId(userId);

        response.setStatusCode(HttpStatus.OK);
        response.setResult(getMember.toString());
        response.setRtnMsg("Find Member");

        return response;
    }
}
