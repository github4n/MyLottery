package com.csy.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.csy.domain.GamePeriod;
import com.csy.domain.PcddProperty;
import com.google.common.collect.Lists;

@Component("pcddPropertyGenerate")
public class PCDDPropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 42L;

	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算pc蛋蛋对应的属性值
	 */
	public PcddProperty createProperty(GamePeriod gamePeriod) {
		PcddProperty pcddProperty = new PcddProperty();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		
		List<String> sOpenNums = Lists.newArrayList(StringUtils.split(gamePeriod.getSopennum(), "|"));
		List<Integer> openNumsList = new ArrayList<>();
		for(String num:sOpenNums) {
			openNumsList.add(Integer.parseInt(num));
		}
		
		generateTopThreeNum(openNumsList);
		
		int resultSum = 0;
		try {
			gamePeriod.setIopennum1(Short.valueOf(""+openNumsList.get(0)));
			gamePeriod.setIopennum2(Short.valueOf(""+openNumsList.get(1)));
			gamePeriod.setIopennum3(Short.valueOf(""+openNumsList.get(2)));
			resultSum = gamePeriod.getIopennum1()+gamePeriod.getIopennum2()+gamePeriod.getIopennum3();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PC蛋蛋前三位开奖号码有误！");
		}
		pcddProperty.setsKey(sKey);
		pcddProperty.setsSum(resultSum);
		
		//---------注意--------
		//修改gamePeriod的20位开奖号码为3位
		gamePeriod.setSopennum(""+gamePeriod.getIopennum1()+","+gamePeriod.getIopennum2()+","+gamePeriod.getIopennum3());
		
		
		//合值14-27为大，合值0-13为小
		if (resultSum >= 14) {
			pcddProperty.setsBigSmall("大");
		} else if (resultSum < 14) {
			pcddProperty.setsBigSmall("小");
		}
		//合值的单双
		if (resultSum % 2 == 1) {
			pcddProperty.setsSingleDouble("单");
		} else {
			pcddProperty.setsSingleDouble("双");
		}
		
		/**
			绿波号码：1, 4, 7, 10, 16, 19, 22, 25；
			蓝波号码：2, 5, 8, 11, 17, 20, 23, 26；
			红波号码：3, 6, 9, 12, 15, 18, 21, 24；
			灰波号码：0, 13, 14, 27。
		 */
		if(isExist("1,4,7,10,16,19,22,25", ""+resultSum)) {
			pcddProperty.setsBoColor("绿波");
		}else if(isExist("2,5,8,11,17,20,23,26", ""+resultSum)) {
			pcddProperty.setsBoColor("蓝波");
		}else if(isExist("3,6,9,12,15,18,21,24", ""+resultSum)) {
			pcddProperty.setsBoColor("红波");
		}else if(isExist("0,13,14,27", ""+resultSum)){
			pcddProperty.setsBoColor("灰波");
		}
		
		//合值 [0-5]为极小，合值[22-27]为极大，其它情况为空值
		if(resultSum > 22) {
			pcddProperty.setsExValue("极大");
		}else if(resultSum <= 5) {
			pcddProperty.setsExValue("极小");
		}else {
			pcddProperty.setsExValue("");
		}
		
		return pcddProperty;
	}

	/**
	 * （幸运28）
	 * 将这20个开奖号码按照由小到大的顺序依次排列;
	 * 取其1-6位开奖号码相加，和值的末位数作为幸运28开奖第一个数值; 
	 * 取其7-12位开奖号码相加，和值的末位数作为幸运28开奖第二个数值；
	 * 取其13-18位开奖号码相加，和值的末位数作为幸运28开奖第三个数值;
	 * 三个数值相加即为幸运28最终的开奖结果！
	 * @param data
	 */
	public static void generateTopThreeNum(List<Integer> data) {
		data.remove(data.size()-1);//删除最后一位（飞盘号）
        data.sort((o1, o2) ->  o1 > o2 ? 1 : o1.equals(o2) ? 0 : -1);
        Integer num1 = 0;
        Integer num2 = 0;
        Integer num3 = 0;
        for (int i = 0; i < data.size(); i++) {
            if (i < 6){
                num1 += data.get(i);
            }else if (i <= 11){
                num2 += data.get(i);
            }else if (i <= 17){
                num3 += data.get(i);
            }
        }
        data.clear();
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);
        String str3 = String.valueOf(num3);
        data.add(Integer.valueOf(str1.substring(str1.length()-1)));
        data.add(Integer.valueOf(str2.substring(str2.length()-1)));
        data.add(Integer.valueOf(str3.substring(str3.length()-1)));
    }
	
	/**
	 * 判断num是否在nums中
	 * @param nums
	 * @param num
	 * @return
	 */
	private boolean isExist(String nums, String num) {
		List<String> _nums = Lists.newArrayList(StringUtils.split(nums, ","));
		return _nums.contains(num);
	}
	
	public static void main(String[] args) {
//		String nums = "1|14|15|16|19|21|31|32|33|42|49|51|55|63|64|66|70|71|75|77|00";
		String nums = "02,04,13,19,21,27,35,36,40,42,48,51,53,59,60,61,65,67,71,76";
		List<String> sOpenNums = Lists.newArrayList(StringUtils.split(nums, ","));
		List<Integer> openNumsList = new ArrayList<>();
		for(String num:sOpenNums) {
			openNumsList.add(Integer.parseInt(num));
		}
		System.out.println(openNumsList);
		generateTopThreeNum(openNumsList);
		System.out.println(openNumsList);
		int resultSum = openNumsList.get(0)+openNumsList.get(1)+openNumsList.get(2);
		System.out.println(resultSum);
	}
}
