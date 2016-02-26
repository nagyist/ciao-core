package uk.nhs.itk.ciao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("javadoc")
public class LoggingBean {
	private static Logger logger = LoggerFactory.getLogger(LoggingBean.class);

	public String logValue(String value) throws Exception {
		logger.info("*** LOG VALUE: {} ***", value);
		return value;
	}
}
