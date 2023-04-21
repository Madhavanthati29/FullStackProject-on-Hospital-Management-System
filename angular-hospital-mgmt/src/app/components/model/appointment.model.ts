import { Doctor } from "./doctor.modal";

export interface Appointment {
    appointmentId: number;
    patientId: number;
    appointmentDate: string;
    appointmentTime: number;
    meridiem: string;
    doctor: Doctor
    patientName: string;
    isAlreadyPrescribed: boolean;
}