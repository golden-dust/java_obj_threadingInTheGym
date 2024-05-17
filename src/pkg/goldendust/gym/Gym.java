package pkg.goldendust.gym;

import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Gym {
	
	private final int totalGymMembers;
	private Map<MachineType, Integer> availableMachines;
	
	public Gym(int totalGymMembers, Map<MachineType, Integer> availableMachines) {
		this.totalGymMembers = totalGymMembers;
		this.availableMachines = availableMachines;
	}
	
	public void openForTheDay() {
		List<Thread> gymMembersRoutines;
		gymMembersRoutines = IntStream.rangeClosed(1, this.totalGymMembers).mapToObj( (id) -> {
			Member member = new Member(id);
			return new Thread(() -> {
				try {
					member.performRoutine();
				} catch (Exception e) {
					System.out.println(e);
				}
			});
		}).collect(Collectors.toList());
		
		// Method referencing syntax (uses double colons)
		gymMembersRoutines.forEach(Thread::start);
	}
	
	private Thread createSupervisor(Thread threads) {
		Thread supervisor = new Thread( () -> {
			while(true) {
				List<String> runningThreads;
				runningThreads = threads.stream().filter(Thread::isAlive)
						.map(Thread::getName).collect(Collectors.toList());
			}
		});
		
		return supervisor;
	}

}
