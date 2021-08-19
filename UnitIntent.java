public class UnitIntent<S extends LinkableSubsystem> implements IntentUnit {
	private final S sub;
	private final Action<S> action;

	public UnitIntent(S sub, Action<S> action) {
		this.sub = sub;
		this.action = action;
	}

	public UnitIntent(
		S sub, LinkCommand cmd, Reality reality, Map<String,MemoryItem> memory
	) {
		this.sub = sub;
		this.action = cmd.runDerive(reality, memory).apply(sub);
	}

	public ActionResult invokeEffect() {
		return this.action.apply(this.sub);
	}

	public S getAssociatedSubsystem() {
		return this.sub;
	}
}
