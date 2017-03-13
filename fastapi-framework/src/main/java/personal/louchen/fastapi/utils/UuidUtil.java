package personal.louchen.fastapi.utils;

import org.slf4j.LoggerFactory;

import java.util.UUID;

public class UuidUtil
{

	protected static org.slf4j.Logger logger = LoggerFactory.getLogger(UuidUtil.class);

	public static String get32UUID()
	{
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args)
	{
		logger.debug(get32UUID());
	}
}
