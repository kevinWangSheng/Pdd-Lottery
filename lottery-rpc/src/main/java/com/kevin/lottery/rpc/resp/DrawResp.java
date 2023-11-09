package com.kevin.lottery.rpc.resp;

import com.kevin.common.Result;
import com.kevin.lottery.rpc.dto.AwardDto;

import java.io.Serializable;

/**抽奖结果
 * @author wang
 * @create 2023-11-09-13:12
 */
public class DrawResp extends Result implements Serializable {
    private AwardDto awardDTO;

    public DrawResp(Integer code, String info) {
        super(code, info);
    }

    public DrawResp() {
    }

    public DrawResp(Integer code, String info, AwardDto awardDTO) {
        super(code, info);
        this.awardDTO = awardDTO;
    }

    public DrawResp(AwardDto awardDTO) {
        this.awardDTO = awardDTO;
    }



}
