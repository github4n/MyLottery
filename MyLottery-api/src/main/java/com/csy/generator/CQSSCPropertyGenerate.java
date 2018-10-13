package com.csy.generator;

import com.csy.domain.CqsscProperty;
import com.csy.domain.GamePeriod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("cqsscPropertyGenerate")
public class CQSSCPropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 5L;
	
	@Override
	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算cqssc对应的属性值
	 */
	public CqsscProperty createProperty(GamePeriod gamePeriod) {
		CqsscProperty cqsscProperty = new CqsscProperty();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		int sumNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3() + gamePeriod.getIopennum4() + gamePeriod.getIopennum5();
		//sKey
		cqsscProperty.setsKey(sKey);
		//总和
		cqsscProperty.setiSum(sumNum);
		//总和大小
		if (sumNum > 22) {
			cqsscProperty.setsSumBigSmall("大");
		} else {
			cqsscProperty.setsSumBigSmall("小");
		}
		//总和单双
		if (sumNum % 2 == 1) {
			cqsscProperty.setsSumSingleDouble("单");
		} else {
			cqsscProperty.setsSumSingleDouble("双");
		}
		//龙虎
		if (gamePeriod.getIopennum1() > gamePeriod.getIopennum5()) {
			cqsscProperty.setsDragonTiger("龙");
			
		} else {
			cqsscProperty.setsDragonTiger("虎");
		}
		//前三、中三、后三
		cqsscProperty.setsTopThree(this.checkThree(gamePeriod.getIopennum1(), gamePeriod.getIopennum2(), gamePeriod.getIopennum3()));
		cqsscProperty.setsMiddleThree(this.checkThree(gamePeriod.getIopennum2(), gamePeriod.getIopennum3(), gamePeriod.getIopennum4()));
		cqsscProperty.setsPostThree(this.checkThree(gamePeriod.getIopennum3(), gamePeriod.getIopennum4(), gamePeriod.getIopennum5()));
		
		return cqsscProperty;
	}
	
	private String checkThree(int openNum1, int openNum2, int openNum3) {
		if (openNum1 == openNum2 && openNum2 == openNum3 && openNum1 == openNum3) {
			return "豹子";
		} else {
			List<String> shunZi = Arrays.asList("1,2,3", "2,3,4", "3,4,5", "4,5,6", "5,6,7", "6,7,8", "7,8,9", "8,9,0", "9,0,1", "0,1,2");

            String sz1 = "2,4,6,8,0";
            for (String sz : shunZi) {
                if (sz.contains(openNum1 + "") && sz.contains(openNum2 + "") && sz.contains(openNum3 + "")) {
                    //防止4,4,6为顺子情况出现
                    if (sz1.contains(openNum1 + "") && sz1.contains(openNum2 + "") && sz1.contains(openNum3 + "")){
                        return "对子";
                    }else if (!sz1.contains(openNum1 + "") && !sz1.contains(openNum2 + "") && !sz1.contains(openNum3 + "")){
                        return "对子";
                    }
                    return "顺子";
                   
                }
            }

			if (openNum1 != openNum2 && openNum2 != openNum3 && openNum1 != openNum3) {
				List<String> banShun = Arrays.asList("1,2", "2,3", "3,4", "4,5", "5,6", "6,7", "7,8", "8,9", "9,0",
						"0,1");

				for (int i = 0; i < banShun.size(); ++i) {
					String bs = (String) banShun.get(i);
					if (bs.indexOf(openNum1 + "") != -1 && bs.indexOf(openNum2 + "") != -1
							|| bs.indexOf(openNum2 + "") != -1 && bs.indexOf(openNum3 + "") != -1
							|| bs.indexOf(openNum1 + "") != -1 && bs.indexOf(openNum3 + "") != -1) {
						return "半顺";
					}
				}

				return "杂六";
			} else {
				return "对子";
			}
		}
	}
}
