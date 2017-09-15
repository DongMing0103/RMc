import java.util.Collection;
import java.util.Map;


public class Ognl {
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			return true;
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object o){
		return !isEmpty(o);
	}
	
	// 批量判断字符，集合，Map不为空
	public static boolean isNotEmptyAnd(Object... objects) {
		for (Object object : objects) {
			if(isEmpty(object)){
				return false;
			}
		}
		return true;
	}
}
