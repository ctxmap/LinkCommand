public class DriveForwardTimed extends LinkCommand {
	private final DriveSubsystem drive;
	private final double duration;

	DriveForwardTimed(DriveSubsystem drive, double duration) {
		this.drive = drive;
		this.duration = duration;
		super(Remember.nothing(), /* subsystems: */ drive);
	}

	@Override
	public Intent runDerive(Reality reality, Map<String,MemoryItem> memory) {
		if (reality.get("elapsedTime") < duration) {
			return new Intent(
				Remember.nothing(), Terminate.NO,
				new UnitIntent(drive, new DriveForward(drive), reality, memory)
			);
		} else {
			return new Intent(
				Remember.nothing(), Terminate.YES,
				new UnitIntent(drive, x -> x.stop())
			);
		}
	}
}
