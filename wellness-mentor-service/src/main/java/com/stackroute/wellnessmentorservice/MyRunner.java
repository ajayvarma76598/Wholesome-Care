package com.stackroute.wellnessmentorservice;

import com.stackroute.wellnessmentorservice.model.AppointmentSlot;
import com.stackroute.wellnessmentorservice.model.Mentor;
import com.stackroute.wellnessmentorservice.model.MentorAuthenResponse;
import com.stackroute.wellnessmentorservice.model.MentorResponse;
import com.stackroute.wellnessmentorservice.repository.MentorRepository;
import com.stackroute.wellnessmentorservice.service.MyTaskExecutor;
import com.stackroute.wellnessmentorservice.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.stackroute.wellnessmentorservice.model.BookedAppointment;

@Component
public class MyRunner implements CommandLineRunner {

    private RabbitMqSender rabbitMqSender;
    private MyTaskExecutor myTaskExecutor;
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    public MyRunner(RabbitMqSender rabbitMqSender,MyTaskExecutor myTaskExecutor) {
        this.rabbitMqSender = rabbitMqSender;
        this.myTaskExecutor = myTaskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        myTaskExecutor.startExecutionAt(15,35,00);
        Mentor yoga = new Mentor("vinish@gmail.com","vinish","Yoga Instructor",4,"../../assets/mentorData/mentorimage/yogaInstructor/krish-yoga-teacher-275x300.jpg" ,"I am one of the best Yoga instructor in india, I practice it from small age , more than 10K hours total experience.",
                "I do my class 12 from MHD School, Delhi . I do graduation in Yoga and Vedic Practice","300 Hour The Himalayan Yoga Institute Pondicherry, South India","I am trained in Patanjali Yoga center","I have more than 10K hours of ecperience","English, Hindi",
                "Power Yoga, HathYoga, Pranayama",new AppointmentSlot[]{new AppointmentSlot("08:00am","09:00am",false),
                new AppointmentSlot("09:00am","10:00am",false)},new BookedAppointment[]{});

        Mentor dietitian = new Mentor("shyam@gmail.com","shyam","Dietitian",4,"../../assets/mentorData/mentorimage/dietitian/Ryan-Fernando.jpg" ,"I am one of the best Dietitian in india, I practice from last 15 years , more than 10K hours total experience.",
                "I do my class 12 from MHD School, Delhi . I do graduation in Nutrients and Diet","Human Nutrition Science Boston University - Summer Term","I am intern in national nutrient center","I have more than 10K hours of experience","English, Hindi",
                "Micro Nutrients, Diet chart and Plans",new AppointmentSlot[]{new AppointmentSlot("08:00am","09:00am",false),
                new AppointmentSlot("10:00am","11:00am",false)},new BookedAppointment[]{});

        Mentor gym = new Mentor("abhishek@gmail.com","Abhishek","Gym Instructor",4,"../../assets/mentorData/mentorimage/gymInstructor/manish.jpeg" ,"I am one of the best Gym instructor in India, I practice it from small age , more than 10K hours total experience.",
                "I do my class 12 from MHD School, Delhi . I do graduation in Body Science","The National Council on Strength & Fitness Certified","I am trained in Delhi Best Fitness center","I have more than 10K hours of experience","English, Hindi",
                "Power gym, Full Body Workout, CardioVascular Exercises",new AppointmentSlot[]{new AppointmentSlot("08:00am","09:00am",false),
                new AppointmentSlot("11:00am","12:00pm",false)},new BookedAppointment[]{});

        mentorRepository.deleteAll();

        mentorRepository.save(yoga);
        rabbitMqSender.send(new MentorResponse(yoga.getEmailId(), yoga.getName(), yoga.getExpertize()),
                new MentorAuthenResponse(yoga.getEmailId(),"vinish123",yoga.getName(),"MENTOR"));

        mentorRepository.save(dietitian);
        rabbitMqSender.send(new MentorResponse(dietitian.getEmailId(), dietitian.getName(), dietitian.getExpertize()),
                new MentorAuthenResponse(dietitian.getEmailId(),"shyam123", dietitian.getName(), "MENTOR"));

        mentorRepository.save(gym);
        rabbitMqSender.send(new MentorResponse(gym.getEmailId(),gym.getName(),gym.getExpertize()),
                new MentorAuthenResponse(gym.getEmailId(),"abhishek123", gym.getName(), "MENTOR"));

    }
}