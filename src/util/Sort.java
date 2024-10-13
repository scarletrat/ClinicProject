package util;

import clinic.Appointment;

import clinic.Patient;
import clinic.Provider;

public class Sort {
    char sortPatient = 'P';
    char sortLocation = 'L';
    char sortAppointment = 'A';
    char sortOffice = 'O';
    char sortImaging = 'I';

    private static void swap(List<Appointment> list,int i, int j){
/**
        Appointment temp = list.get(j);
        list.get(j) = list.get(i);
        list.get(i) = temp;
*/
        Appointment temp = list.get(j);
        Appointment mininum = list.get(j);
        mininum = list.get(i);
        Appointment ii = list.get(i);
        ii = temp;
    }

    private static void sortPatient(List<Appointment> list){
        if(list.isEmpty()){
            return;
        }
        int size = list.size();
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(((Patient)list.get(j).getPatient()).compareTo(((Patient)list.get(min).getPatient())) < 0) {
                    min = j;
                }
                else if(list.get(j).compareTo(list.get(min))<0 &&
                        ((Patient)list.get(j).getPatient()).compareTo(((Patient)list.get(min).getPatient())) == 0){
                    min =j;
                }
            }
            swap(list,i,min);
        }
    }

    public static void sortLocation(List<Appointment> list) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (((Provider)list.get(j).getProvider()).getLocation().getCounty().compareTo(((Provider)list.get(min).getProvider()).getLocation().getCounty()) < 0) {
                    min = j;
                } else if (list.get(j).compareTo(list.get(min)) < 0 &&
                        ((Provider)list.get(j).getProvider()).getLocation().getCounty().compareTo(((Provider)list.get(min).getProvider()).getLocation().getCounty()) == 0) {
                    min = j;
                }
            }
            swap(list, i, min);
        }
    }

    public static void sortAppointment(List<Appointment> list){
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
                else if(list.get(j).getProvider().compareTo(list.get(min).getProvider())<0 &&
                        list.get(j).compareTo(list.get(min)) == 0){
                    min =j;
                }
            }
            swap(list,i,min);
        }
    }

    public static void appointment(List<Appointment> list, char key) {
        switch(key){
            case 'P':
                sortPatient(list);
                break;
            case 'L':
                sortLocation(list);
                break;
            case 'A':
                sortAppointment(list);
                break;
            case 'O':
                //sortOFFICE(PO)
                break;
            case 'I':
                //sortImaging(PI)
                break;
        }
    }

    public static void provider(List<Provider> list) {
        if(list.isEmpty()){
            return;
        }
        int size = list.size();
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(list.get(j).getProfile().compareTo(list.get(min).getProfile()) < 0) {
                    min = j;
                }
            }
            Provider temp = list.get(min);
            Provider mininum = list.get(min);
            mininum = list.get(i);
            Provider ii = list.get(i);
            ii = temp;
        }

    }

}
