import { BookedAppointment } from "./BookedAppointment";
import { AppointmentSlot } from "./AppointmentSlot";
export class Mentor{
    emailId: string;
    name: string;
    expertize: string;
    rating: number;
    imagePath: String;
    about: String;
    education: String;
    certification: String;
    training: String;
    experience: String;
    language:String;
    specialities: String;
    appointmentSlots: AppointmentSlot[];
    bookedAppointments:BookedAppointment[];

    constructor(){
        this.emailId='';
        this.name='';
        this.expertize='';
        this.rating=0;
        this.imagePath='';
        this.about='';
        this.education='';
        this.certification='';
        this.training='';
        this.experience='';
        this.language='';
        this.specialities='';
        this.appointmentSlots = [new AppointmentSlot()];
        this.bookedAppointments=null;

    }

}