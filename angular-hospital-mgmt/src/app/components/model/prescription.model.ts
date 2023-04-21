import { Appointment } from "./appointment.model";

export interface Prescription {
    prescriptionId: number;
    patientId: number;
    doctorId: number;
    status: string;
    bookingAppointment: Appointment;
    isPaid: boolean;
}