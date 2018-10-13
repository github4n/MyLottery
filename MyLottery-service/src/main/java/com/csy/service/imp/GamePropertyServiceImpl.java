package com.csy.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csy.domain.Ahk3Propery;
import com.csy.domain.Bjkl8Property;
import com.csy.domain.Bjpk10Property;
import com.csy.domain.CqsscProperty;
import com.csy.domain.Fc3dProperty;
import com.csy.domain.GamePeriod;
import com.csy.domain.Gd11x5Propery;
import com.csy.domain.GdklsfProperty;
import com.csy.domain.Gxk3Propery;
import com.csy.domain.HnklsfPropery;
import com.csy.domain.JsftProperty;
import com.csy.domain.Jsk3Propery;
import com.csy.domain.JsscProperty;
import com.csy.domain.JssscProperty;
import com.csy.domain.Jx11v5Propery;
import com.csy.domain.MlaftProperty;
import com.csy.domain.PcddProperty;
import com.csy.domain.Pl3Property;
import com.csy.domain.SdcPropery;
import com.csy.domain.ShsslPropery;
import com.csy.domain.SyytjPropery;
import com.csy.domain.TjklsfPropery;
import com.csy.domain.TjsscPropery;
import com.csy.domain.XjsscProperty;
import com.csy.domain.XyncPropery;
import com.csy.mapper.Ahk3ProperyMapper;
import com.csy.mapper.Bjkl8PropertyMapper;
import com.csy.mapper.Bjpk10PropertyMapper;
import com.csy.mapper.CqsscPropertyMapper;
import com.csy.mapper.Fc3dPropertyMapper;
import com.csy.mapper.Gd11x5ProperyMapper;
import com.csy.mapper.GdklsfPropertyMapper;
import com.csy.mapper.Gxk3ProperyMapper;
import com.csy.mapper.HnklsfProperyMapper;
import com.csy.mapper.JsftPropertyMapper;
import com.csy.mapper.Jsk3ProperyMapper;
import com.csy.mapper.JsscPropertyMapper;
import com.csy.mapper.JssscPropertyMapper;
import com.csy.mapper.Jx11v5ProperyMapper;
import com.csy.mapper.MlaftPropertyMapper;
import com.csy.mapper.PcddPropertyMapper;
import com.csy.mapper.Pl3PropertyMapper;
import com.csy.mapper.SdcProperyMapper;
import com.csy.mapper.ShsslProperyMapper;
import com.csy.mapper.SyytjProperyMapper;
import com.csy.mapper.TjklsfProperyMapper;
import com.csy.mapper.TjsscProperyMapper;
import com.csy.mapper.XjsscPropertyMapper;
import com.csy.mapper.XyncProperyMapper;
import com.csy.service.IGamePropertyService;


//彩种属性
@Service
public class GamePropertyServiceImpl implements IGamePropertyService{
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
	private JssscPropertyMapper jssscPropertyMapper;  //极速时时彩
	@Autowired
	private JsscPropertyMapper jsscPropertyMapper;  //极速赛车
	@Autowired
	private MlaftPropertyMapper mlaftPropertyMapper;  //幸运飞艇
	@Autowired
	private SdcProperyMapper sdcProperyMapper;  //圣地彩
	@Autowired
	private SyytjProperyMapper syytjProperyMapper;  //十一运夺金
	@Autowired
	private JsftPropertyMapper  jsftPropertyMapper;  //极速飞艇
	@Autowired
	private PcddPropertyMapper pcddPropertyMapper; //PC蛋蛋
	

	/**
	 * 通过彩种对象获取彩种对应的属性信息
	 * @param gamePeriod  完整的彩种信息包括了属性信息
	 * @return
	 */
	public GamePeriod getGamePropertyClassByGameId(GamePeriod gamePeriod) {
		if (gamePeriod.getIgameid() == 1) {
			Fc3dProperty fc3d = fc3dPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(fc3d); 
			return gamePeriod; 
		}
		if (gamePeriod.getIgameid() == 2) {
			Pl3Property pl3 = pl3PropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(pl3);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 3) {
		    ShsslPropery shsslPropery = shsslPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
		    gamePeriod.setProperty(shsslPropery);
		    return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 4) {
			TjsscPropery tjsscPropery = tjsscProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(tjsscPropery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 5) {
			CqsscProperty cqsscProperty = cqsscPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(cqsscProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 7) {
			XjsscProperty xjsscProperty = xjsscPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(xjsscProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 9) {
			Bjpk10Property bjpk10Property = bjpk10PropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(bjpk10Property);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 10) {
			Jsk3Propery jsk3Propery = jsk3ProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(jsk3Propery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 11) {
			Ahk3Propery ahk3Propery = ahk3ProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(ahk3Propery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 14) {
			Jx11v5Propery jx11v5Propery = jx11v5ProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(jx11v5Propery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 15) {
			Gd11x5Propery gd11x5Propery = gd11x5ProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(gd11x5Propery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 17) {
			Gxk3Propery gxk3Propery = gxk3ProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(gxk3Propery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 23) {
			Bjkl8Property bjkl8Property = bjkl8PropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(bjkl8Property);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 26) {
			TjklsfPropery tjklsfPropery = tjklsfProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(tjklsfPropery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 27) {
			GdklsfProperty gdklsfProperty = gdklsfPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(gdklsfProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 28) {
			XyncPropery xyncPropery = xyncProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(xyncPropery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 29) {
			HnklsfPropery hnklsfPropery = hnklsfProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(hnklsfPropery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 36) {
			JssscProperty jssscProperty = jssscPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(jssscProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 37) {
			JsscProperty jsscProperty = jsscPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(jsscProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 38) {
			MlaftProperty mlaftProperty = mlaftPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(mlaftProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 39) {
			SdcPropery sdcPropery = sdcProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(sdcPropery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 40) {
			SyytjPropery syytjPropery = syytjProperyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(syytjPropery);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 41) {
			JsftProperty jsftProperty = jsftPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(jsftProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 42) {
			PcddProperty pcddProperty = pcddPropertyMapper.selectByPrimaryKey(gamePeriod.getSkey());
			gamePeriod.setProperty(pcddProperty);
			return gamePeriod;
		}
		if (gamePeriod.getIgameid() == 18) {
			
		}
		return gamePeriod;
	}
}
