**Steps to run this project in the machine**

    1. Get the Camunda 8 platform running in the machine
        i. Clone the docker compose project of self-managed camunda platform from here - https://github.com/camunda/camunda-platform
        ii. Startup the docker service and up all the camunda services using its docker compose file.
    2. Download the Camunda Modeler opensource package for desktop from here - https://camunda.com/download/modeler
    3. From the resources/ package of this project , open the files with .bpmn extension in Camunda Modeler.
    4. Clean install the project using the 'mvn clean install'
    5. Run the Project using 'mvn spring-boot:run'


**Operations can be performed through this project.**


1. Launch the instances of the flows through the Camunda Modeler as showcased in the sync-up call.
2. Create a rest end point to trigger the flow using below snippet.
 

        @Autowired
        ZeebeClient zeebe;
    
        var bpmnProcessId = "process-payments";
        var event = zeebe.newCreateInstanceCommand()
        .bpmnProcessId(bpmnProcessId)
        .latestVersion()
        .variable("total",100)
        .send()
        .join();
    
        LOGGER.info("Process Payment started on instance : {} ",event.getProcessInstanceKey());

