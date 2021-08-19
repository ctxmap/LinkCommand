public class DriveForwardTimed extends LinkCommand {
	DriveForwardTimed(DriveSubsystem drive, double duration) {
		this.drive = drive;
		this.duration = duration;
		super(Remember.nothing(), /* subsystems: */ drive);
	}

	@Override
	public Intent run_derive(Reality reality, Map<String,Object> memory) {
		if (reality.get("elapsedTime") < duration) {
			return new Intent(
				Remember.nothing(), Terminate.NO,
				new UnitIntent(drive, new DriveForward(drive))
			);
		} else {
			return new Intent(
				Remember.nothing(), Terminate.YES,
				new UnitIntent(drive, x -> x.stop())
			);
		}
	}
}
