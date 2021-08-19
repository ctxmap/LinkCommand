public class Intent implements Function<LinkableSubsystem,Optional<IntentUnit>> {
	public Intent(Remember remember, Terminate terminate, IntentUnit... units) {
		this.remember = remember;
		this.terminate = terminate;
		this.units = units;
	}

	public Remember getRemember() { return this.remember; }
	public Terminate getTerminate() { return this.terminate; }

	public Optional<IntentUnit> apply(LinkableSubsystem sub) {
		Optional<IntentUnit> buffer = Optional<IntentUnit>.empty();
		for (i : this.units) {
			if (i.getAssociatedSubsystem().hasSameTarget(sub)) {
				if (buffer.isPresent()) {
					throw new LinkableSubsystemMultipleChoiceException(
						buffer.get().getClass().getName()
					);
				}
				buffer = Optional.ofNullable(i);
			}
		}
		return buffer;
	}
}
