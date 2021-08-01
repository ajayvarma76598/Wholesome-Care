import { AppointmentSlot } from "../mentor-profile/AppointmentSlot";

export class UserAppointment{
    appointment:AppointmentSlot;
    mentorEmail:String;
    mentorName:String;
    mentorExpertize: String;
    constructor(){
        this.appointment = new AppointmentSlot();
        this.mentorEmail = '';
        this.mentorExpertize = '';
        this.mentorName = '';
    }
}