@RestController
@RequestMapping("/rules")
public class PriorityRuleController {

    private final PriorityRuleService service;

    public PriorityRuleController(PriorityRuleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Object all() {
        return service.getAllRules();
    }
}
