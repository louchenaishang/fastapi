package personal.louchen.fastapi.rest;

import org.springframework.beans.factory.InitializingBean;

public class ApiBaseController implements InitializingBean{

	/**
	 * 处理API信息
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		RestResourceUtil.addRestDescriptorByClass(this.getClass());
	}
	
	

}
