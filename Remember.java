public class Remember implements UnaryOperator<Map<String,Object>> {
	public final Map<String,Object> update;

	public Remember(Map<String,Object> update) {
		this.update = update;
	}

	public static just(String key, Object value) {
		return new Remember(Map.of(key, value));
	}

	public static nothing() {
		return new Remember(new HashMap<String,Object>());
	}

	public Map<String,Object> apply(Map<String,Object> original) {
		Map<String,Object> buffer = new HashMap<String,Object>();
		buffer = new HashMap<>(original);
		buffer.putAll(this.update);
		return buffer;
	}
}
