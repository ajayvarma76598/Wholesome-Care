package com.stackroute.wellnessservice;

import com.stackroute.wellnessservice.model.Helpline;
import com.stackroute.wellnessservice.service.HelplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private HelplineService helplineService;
    private Helpline helpline=new Helpline();
    @Autowired
    public MyRunner(HelplineService helplineService){
        this.helplineService=helplineService;
    }
    @Override
    public void run(String... args) throws Exception{
        helpline.setCityName("Vizag");
        helpline.setHelplineNumber("67676776");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Itanagar");
        helpline.setHelplineNumber("2212702");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Dispur");
        helpline.setHelplineNumber("2540278");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Patna");
        helpline.setHelplineNumber("0612-2201977-78");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Raipur");
        helpline.setHelplineNumber("2749194");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Delhi");
        helpline.setHelplineNumber("011-24311918");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("New Delhi");
        helpline.setHelplineNumber("011-23389090");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Panaji");
        helpline.setHelplineNumber("0832-2252525");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Ghandhinagar");
        helpline.setHelplineNumber("1800-233-3330");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Chandighar");
        helpline.setHelplineNumber("2749194");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Haryana");
        helpline.setHelplineNumber("2642150");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Shimla");
        helpline.setHelplineNumber("0177-2621711");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Ranchi");
        helpline.setHelplineNumber("9810015104");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Banglore");
        helpline.setHelplineNumber("080-25722573");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Thiruvananthapuram");
        helpline.setHelplineNumber("91-484-2540530");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Mumbai");
        helpline.setHelplineNumber("022-24131212");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Imphal");
        helpline.setHelplineNumber("0385-2440100");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Shillong");
        helpline.setHelplineNumber("2222277");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Aizwal");
        helpline.setHelplineNumber("0389-2334682");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Kohima");
        helpline.setHelplineNumber("0370-2902068");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Bhuvaneswar");
        helpline.setHelplineNumber("0674-2392115");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Chandighar");
        helpline.setHelplineNumber("0183-2535322");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Jaipur");
        helpline.setHelplineNumber("0141-2619725");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Gangtok");
        helpline.setHelplineNumber("03592-284416");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Chennai");
        helpline.setHelplineNumber("044 -2464000");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Hyderabad");
        helpline.setHelplineNumber("040-66202001");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Agartala");
        helpline.setHelplineNumber("0381-222 5774");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Lucknow");
        helpline.setHelplineNumber("0522-2838128");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Dehradun");
        helpline.setHelplineNumber("0135-2712685");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Kolkata");
        helpline.setHelplineNumber("033-40447437");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Ahmedabad");
        helpline.setHelplineNumber("079-26305544");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Srinagar");
        helpline.setHelplineNumber("18001807020");
        helplineService.saveHelpline(helpline);
        helpline.setCityName("Jamshedpur");
        helpline.setHelplineNumber("0657-6453841");
        helplineService.saveHelpline(helpline);


    }
}
