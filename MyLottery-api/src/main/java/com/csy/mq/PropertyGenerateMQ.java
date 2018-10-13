package com.csy.mq;

import com.alibaba.fastjson.JSON;
import com.csy.common.util.DateUtil;
import com.csy.common.util.RedisUtil;
import com.csy.domain.*;
import com.csy.generator.PropertyGenerate;
import com.csy.mapper.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
@RabbitListener(queues = "PropertyGenerate")
@Transactional//事务支持
public class PropertyGenerateMQ {
	@Autowired
	private List<PropertyGenerate> generates;
	@Autowired
	private GamePeriodMapper gamePeriodMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private Fc3dPropertyMapper fc3dPropertyMapper;  //福彩3d属性对象
	@Autowired
	private Pl3PropertyMapper pl3PropertyMapper;  //排列3属性对象
	@Autowired
	private ShsslProperyMapper shsslPropertyMapper;  //上海时时乐属性对象
	@Autowired
	private TjsscProperyMapper tjsscProperyMapper;  //天津时时彩属性对象
	@Autowired
	private CqsscPropertyMapper cqsscPropertyMapper;  //重庆时时彩
	@Autowired 
	private XjsscPropertyMapper xjsscPropertyMapper;  //新缰时时彩
	@Autowired
	private Bjpk10PropertyMapper bjpk10PropertyMapper;  //北京PK10
	@Autowired
	private Jsk3ProperyMapper jsk3ProperyMapper;  //江苏快3
	@Autowired
	private Ahk3ProperyMapper ahk3ProperyMapper;  //安徽快3
	@Autowired
	private Jx11v5ProperyMapper jx11v5ProperyMapper;  //江西11选5
	@Autowired
	private Gd11x5ProperyMapper gd11x5ProperyMapper;  //广东11选5
	@Autowired
	private Gxk3ProperyMapper gxk3ProperyMapper;  //广西快3
	@Autowired
	private Bjkl8PropertyMapper bjkl8PropertyMapper;  //北京快乐8
	@Autowired
	private TjklsfProperyMapper tjklsfProperyMapper;  //天津快乐十分
	@Autowired
	private GdklsfPropertyMapper gdklsfPropertyMapper;  //广东快乐十分
	@Autowired
	private XyncProperyMapper xyncProperyMapper;  //幸运农场
	@Autowired
	private HnklsfProperyMapper hnklsfProperyMapper;  //湖南快乐十分
	@Autowired
	private MlaftPropertyMapper mlaftPropertyMapper;//幸运飞艇
	@Autowired
	private PcddPropertyMapper pcddPropertyMapper;//PC蛋蛋
    @Autowired
    private JsftPropertyMapper jsftPropertyMapper;//极速快艇
	@Autowired
	private JsscPropertyMapper jsscPropertyMapper;//极速赛车
	@Autowired
	private Azxy10PropertyMapper azxy10PropertyMapper;//澳洲幸运10

	@RabbitHandler
	public void receive(String gamePeriodRemote) {
		System.out.println("接收到MQ数据(String)："+gamePeriodRemote);
		//获取MQ传递过来的Json数据并解析
		GamePeriod gamePeriod = JSON.parseObject(gamePeriodRemote, GamePeriod.class);
		
		//填充开奖号码
		fillNumsWithOpenNum(gamePeriod);
		
		Iterator<PropertyGenerate> iterator = generates.iterator();
		PropertyGenerate generate;
		
		do {
			if (!iterator.hasNext()) {
				Collections.emptyList();
			}
			generate = (PropertyGenerate) iterator.next();
		} while (!generate.handle(gamePeriod.getIgameid()));
		
//		System.out.println("属性成功生成！！！"+ generate.createProperty(gamePeriod));
		
		
		//1. gamePeriod.getiGameId()
		int gameId = Integer.parseInt(""+gamePeriod.getIgameid());
		//2. 通过gameId值来判断执行的路径
		switch(gameId){
		case 1: //福彩3d
			//計算福彩3d的屬性
			Fc3dProperty fc3dProperty = (Fc3dProperty) generate.createProperty(gamePeriod);
			//設置屬性
			gamePeriod.setProperty(JSON.toJSONString(fc3dProperty));
			//同步到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//同步福彩3d的屬性到mysql中
			fc3dPropertyMapper.saveOrUpdate(fc3dProperty);
			break;

		case 2: //排列3
			//計算排列的屬性
			Pl3Property pl3Property = (Pl3Property) generate.createProperty(gamePeriod);
			//設置屬性
			gamePeriod.setProperty(JSON.toJSONString(pl3Property));
			//同步到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//同步福彩3d的屬性到mysql中
			pl3PropertyMapper.saveOrUpdate(pl3Property);
			break;

		case 3: //上海时时乐
			//计算上海时时乐的属性
			ShsslPropery shsslProperty = (ShsslPropery) generate.createProperty(gamePeriod);
			//设置属性
			gamePeriod.setProperty(JSON.toJSONString(shsslProperty));
			//保存到mysql
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//保存到redis中
			insertRedisWithIndex(gamePeriod);
			//同步上海时时乐的属性到mysql中
			shsslPropertyMapper.saveOrUpdate(shsslProperty);
			break;

		case 4: //天津时时彩
			//使用creatProperty创建出专属天津时时彩的属性值
			TjsscPropery tjsscPropery = (TjsscPropery) generate.createProperty(gamePeriod);
			// 使用上一步的返回值更新天津时时彩的属性
			gamePeriod.setProperty(JSON.toJSONString(tjsscPropery));
			//同步GamePeriod到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步GamePeriod到redis中
			insertRedisWithIndex(gamePeriod);
			//同步GdklsfProperty属性到mysql中
			tjsscProperyMapper.saveOrUpdate(tjsscPropery);
			break;

		case 5://重庆时时彩
			//计算彩种的属性
			CqsscProperty cqsscProperty = (CqsscProperty)generate.createProperty(gamePeriod);
			//更新gamePeriod的property字段(JSON对象)
			gamePeriod.setProperty(JSON.toJSONString(cqsscProperty));
			//GamePeriod入库
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//GamePeriod写入Redis
			insertRedisWithIndex(gamePeriod);
			//属性值写入数据库
			cqsscPropertyMapper.saveOrUpdate(cqsscProperty);
//			System.out.println("Cqssc receive success : "+gamePeriod);
			break;


		case 7://新疆时时彩
			XjsscProperty xjsscProperty = (XjsscProperty) generate.createProperty(gamePeriod);
			//更新gamePeriod的property字段(JSON对象)
			gamePeriod.setProperty(JSON.toJSONString(xjsscProperty));
			//GamePeriod入库
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//GamePeriod写入Redis
			insertRedisWithIndex(gamePeriod);
			//属性值写入数据库
			xjsscPropertyMapper.saveOrUpdate(xjsscProperty);
			break;

		case 9://北京pk10
			Bjpk10Property bjpk10Property = (Bjpk10Property)generate.createProperty(gamePeriod);
			gamePeriod.setProperty(JSON.toJSONString(bjpk10Property));
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			insertRedisWithIndex(gamePeriod);
			bjpk10PropertyMapper.saveOrUpdate(bjpk10Property);
//			System.out.println("Bjpk10 receive success : "+gamePeriod);
			break;

		case 10 : //江苏快3
			//计算江苏快3的属性
			Jsk3Propery jsk3Propery = (Jsk3Propery) generate.createProperty(gamePeriod);
			//设置属性
			gamePeriod.setProperty(JSON.toJSONString(jsk3Propery));
			//同步到mysql
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//更新江苏快3的属性
			jsk3ProperyMapper.saveOrUpdate(jsk3Propery);
			break;

		case 11: //安徽快3
			//计算安徽快3的属性
			Ahk3Propery ahk3Propery = (Ahk3Propery) generate.createProperty(gamePeriod);
			//设置属性
			gamePeriod.setProperty(JSON.toJSONString(ahk3Propery));
			//同步到mysql
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//更新安徽快3的属性
			ahk3ProperyMapper.saveOrUpdate(ahk3Propery);
			break;

		case 14 : //江西11选5
			//计算江西11选5的属性
			Jx11v5Propery jx11v5roperty = (Jx11v5Propery) generate.createProperty(gamePeriod);
			//设置属性
			gamePeriod.setProperty(JSON.toJSONString(jx11v5roperty));
			//同步到mysql
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis
			insertRedisWithIndex(gamePeriod);
			//更新江西11选5的属性
			jx11v5ProperyMapper.saveOrUpdate(jx11v5roperty);
			break;

		case 15: //廣東11選5
			//獲取廣東11選5的屬性
			Gd11x5Propery gd11x5Propery = (Gd11x5Propery) generate.createProperty(gamePeriod);
			//設置屬性
			gamePeriod.setProperty(JSON.toJSONString(gd11x5Propery));
			//同步GamePeriod到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//同步Gd11x5Property到mysql中
			gd11x5ProperyMapper.saveOrUpdate(gd11x5Propery);
			break;

		case 17: //廣西快3
			Gxk3Propery gxk3Propery = (Gxk3Propery) generate.createProperty(gamePeriod);
			// 使用上一步的返回值更新廣西快3的属性
			gamePeriod.setProperty(JSON.toJSONString(gxk3Propery));
			//同步GamePeriod到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步GamePeriod到redis中
			insertRedisWithIndex(gamePeriod);
			//同步GdklsfProperty属性到mysql中
			gxk3ProperyMapper.saveOrUpdate(gxk3Propery);
			break;


		case 23://北京快乐8(未完成,待討論)
            //计算北京快乐8的属性
			Bjkl8Property bjkl8Property = (Bjkl8Property)generate.createProperty(gamePeriod);
			//设置北京快乐8的属性
			gamePeriod.setProperty(JSON.toJSONString(bjkl8Property));
			//同步到mySQL
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//更新属性表
			bjkl8PropertyMapper.saveOrUpdate(bjkl8Property);
            break;


		case 27://广东快乐十分
			//使用creatProperty创建出专属广东快乐十分的属性值
			GdklsfProperty gdklsfproperty = (GdklsfProperty) generate.createProperty(gamePeriod);
			// 使用上一步的返回值更新广东快乐十分的属性
			gamePeriod.setProperty(JSON.toJSONString(gdklsfproperty));
			//同步GamePeriod到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步GamePeriod到redis中
			insertRedisWithIndex(gamePeriod);
			//同步GdklsfProperty属性到mysql中
			gdklsfPropertyMapper.saveOrUpdate(gdklsfproperty);
			break;

		case 28: //重庆幸运农场
			//使用creatProperty创建出专属重庆幸运农场的属性值
			XyncPropery xyncProperty = (XyncPropery) generate.createProperty(gamePeriod);
			// 使用上一步的返回值更新专属重庆幸运农场的属性
			gamePeriod.setProperty(JSON.toJSONString(xyncProperty));
			//同步GamePeriod到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步GamePeriod到redis中
			insertRedisWithIndex(gamePeriod);
			//同步xyncProperty属性到mysql中
			xyncProperyMapper.saveOrUpdate(xyncProperty);
//			updateRedis(gamePeriod);
			break;

		case 33://澳洲幸运10
			//计算澳洲幸运10的属性
			Azxy10Property azxy10Property = (Azxy10Property) generate.createProperty(gamePeriod);
			//设置属性
			gamePeriod.setProperty(JSON.toJSONString(azxy10Property));
			//同步到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步到redis中
			insertRedisWithIndex(gamePeriod);
			//更新属性
			azxy10PropertyMapper.saveOrUpdate(azxy10Property);
//			updateRedis(gamePeriod);
			break;

		case 37://极速赛车
			//使用creatProperty创建出专属极速赛车的属性值
			JsscProperty jsscProperty = (JsscProperty) generate.createProperty(gamePeriod);
			// 使用上一步的返回值更新极速赛车的属性
			gamePeriod.setProperty(JSON.toJSONString(jsscProperty));
			//同步GamePeriod到mysql中
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			//同步GamePeriod到redis中
			insertRedisWithIndex(gamePeriod);
			//同步xyncProperty属性到mysql中
			jsscPropertyMapper.saveOrUpdate(jsscProperty);
//			updateRedis(gamePeriod);
			break;

		case 38://幸运飞艇
			MlaftProperty mlaftProperty = (MlaftProperty)generate.createProperty(gamePeriod);
			gamePeriod.setProperty(JSON.toJSONString(mlaftProperty));
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			insertRedisWithIndex(gamePeriod);
			mlaftPropertyMapper.saveOrUpdate(mlaftProperty);
//			System.out.println("Mlaft receive success : "+gamePeriod);
			break;
			
		case 42://pc蛋蛋
			gamePeriod.setIopen((short)1);
            gamePeriod.setIstatus((short)3);
            gamePeriod.setImanualopen((short)0);
            gamePeriod.setDrealopen(DateUtil.getNowTime());
            
			PcddProperty pcddProperty = (PcddProperty)generate.createProperty(gamePeriod);
			gamePeriod.setProperty(JSON.toJSONString(pcddProperty));
			gamePeriodMapper.saveOrUpdate(gamePeriod);
			insertRedisWithIndex(gamePeriod);
			pcddPropertyMapper.saveOrUpdate(pcddProperty);
			break;

        case 41://极速快艇
            //使用creatProperty创建出专属极速快艇的属性值
            JsftProperty jsftproperty = (JsftProperty) generate.createProperty(gamePeriod);
            //使用上一步的返回值更新极速快艇的属性
            gamePeriod.setProperty(JSON.toJSONString(jsftproperty));
            //同步GamePeriod到mysql中
            gamePeriodMapper.saveOrUpdate(gamePeriod);
            //同步GamePeriod到redis中
            insertRedisWithIndex(gamePeriod);
            //同步JsftProperty属性到mysql中
            jsftPropertyMapper.saveOrUpdate(jsftproperty);
            break;



//		case 42:
//				Bjpk10Property jsftProperty = (Bjpk10Property)generate.createProperty(gamePeriod);
//				bjpk10PropertyMapper.insert(jsftProperty);
//				System.out.println("Receive success : "+gamePeriod);
//			break;



		default:
			System.out.println("未处理的彩种["+gamePeriod.getIgameid()+"]，sopennum="+gamePeriod.getSopennum());
			break;
		}
		
	}
	
	/**=========基于scan的实现方式========
	[key]
		彩种id:开奖日期:彩种期数
		GameID9:2018-08-27:678910
		GameID5:2018-08-27:20180827081
	[value]
		对应的JSON对象
		每个key的过期时间为一周
	 * @param gp
	 */
	private void insertRedisWithScan(GamePeriod gp) {
		String parserDate = DateUtil.formatDate(gp.getDopentime(), DateUtil.PATTERN_SIMPLE_DATE);
		String key = "GameID"+gp.getIgameid()+":"+parserDate+":"+gp.getSgameperiod();
		redisUtil.setValue(key, gp, RedisUtil.ONE_WEEK_IN_SECONDS);
	}
	
	
	/**=========基于索引的实现方式=========
	 * [key-value]
	 * sKey->JSON对象
	 * 
	 * [ZSet]
	 * key:
	 * 		GameID9:2018-08-28
	 * value:
	 * 		9.678910|9.678911|9.678912...
	 */
	private void insertRedisWithIndex(GamePeriod gamePeriod) {
		String parserDate = DateUtil.formatDate(gamePeriod.getDopentime(), DateUtil.PATTERN_SIMPLE_DATE);
		String key = "GameID"+gamePeriod.getIgameid()+":"+parserDate;
		//sKey-JSON
		//9.678910-{"xx":"zz"}
		redisUtil.setValue(gamePeriod.getSkey(), gamePeriod, RedisUtil.ONE_WEEK_IN_SECONDS);
		
		//GameID9:2018-08-28
		redisUtil.setZSet(key, gamePeriod.getSkey(), Long.parseLong(gamePeriod.getSgameperiod()), RedisUtil.ONE_WEEK_IN_SECONDS);
	}
	

	/**
	 * 拆分OpenNum并将数字填入对应位置
	 * 
	 * 注意：北京快乐8有20位号码，做了特殊处理！
	 * （PC蛋蛋的gameId=42，由北京快乐8变种成来！）
	 * 
	 * @param gamePeriod
	 */
	private void fillNumsWithOpenNum(GamePeriod gamePeriod) {
		
		if(StringUtils.isNotBlank(gamePeriod.getSopennum()) && (gamePeriod.getIgameid() != 42L)
                && (gamePeriod.getIgameid() != 23)) {
			List<String> sOpenNums = Lists.newArrayList(StringUtils.split(gamePeriod.getSopennum(), "|"));

			for (int i = 0; i < CollectionUtils.size(sOpenNums); ++i) {
				try {
					FieldUtils.writeField(gamePeriod, "iopennum" + (i + 1), Short.valueOf((String) sOpenNums.get(i)), true);
				} catch (IllegalAccessException var9) {
					System.out.println(var9);
				}
			}
		}
		gamePeriod.setIopen((short)1);
		gamePeriod.setIstatus((short)3);
		gamePeriod.setImanualopen((short)0);
		gamePeriod.setDrealopen(DateUtil.getNowTime());
	}

	
	
	public void forSave(GamePeriod gamePeriod, BaseDomain obj) {
		//CqsscProperty cqsscProperty = new CqsscProperty();
		//更新gamePeriod的property字段(JSON对象)
		gamePeriod.setProperty(JSON.toJSONString(obj));
		//GamePeriod入库
		gamePeriodMapper.save(gamePeriod);
		//GamePeriod写入Redis
		insertRedisWithScan(gamePeriod);
		//属性值写入数据库
		cqsscPropertyMapper.insert((CqsscProperty)obj);
	}


}
