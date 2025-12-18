@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public Complaint submit(@RequestBody ComplaintRequest req) {
        return service.submitComplaint(req);
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> user(@PathVariable Long userId) {
        return service.getUserComplaints(userId);
    }

    @GetMapping("/prioritized")
    public List<Complaint> prioritized() {
        return service.getPrioritizedComplaints();
    }
}
