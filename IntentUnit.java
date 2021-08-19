public interface IntentUnit<S extends LinkableSubsystem> {
	public ActionResult invokeEffect();
	public S getAssociatedSubsystem();
}
