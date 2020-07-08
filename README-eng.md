### Car-rental (ITALIAN project)
# OOP application to manage a car rental
Before proceeding with the description of the methods related to each class, I would like to give some general explanations about the logic behind the ProjectAutonoleggio program. 
This, in fact, is organized in six classes: Vehicle, which provides two subclasses such as Car and Van, Archive, Reservation, ArchiveReservation and ManagementAutonoleggio, or the main one.
The initialized vehicles are inserted in the Archive, as is the case for bookings, which are managed by the Reservations Archive. The choice to keep the bookings separate from the vehicles lies in the concept that they are two separate entities, but linked by the fact that the vehicle is an instance variable of the booking. 
The main is thus the class that uses the objects of all the other classes made. However, it must be specified that the controls on the instance variables are never made in the main: in fact, this responsibility is delegated exclusively to the classes concerned. These return feedback through exceptions and return values, which are then managed by the main.

# Program User Manual
The program opens asking you to enter the name of the registry, if it is not yet present the user is warned that the file will be created the first time it is saved. 

The main menu is then shown with the possible commands. 
(The following paragraphs present all the program commands following the interface menu).

[A]ggiungi un mezzo all'archivio
If you want to add a new vehicle, you will be asked whether it will be a Van or a Car, at this point you will proceed with filling in the required fields according to your choice. The vehicle is then not initialized until all the fields have been completed. At first there may be an error, because the checks are not made in run time but are delegated to the manufacturer of the vehicle, which will launch an exception - then managed by the main - if they are not correct. Once the vehicle is initialized, it is added to the archive, and if the operation is successful, a positive value is returned to the main.

[R]imuovi un mezzo dall'archivio
Before removing a medium, a check is made in the booking archive if there are no bookings for the medium in question. If the vehicle is already booked, it will not be possible to remove it from the car rental vehicle archive, otherwise yes.

[V]isualizza le informazioni di tutti i mezzi a disposizione
If the archive of the vehicles managed by the car rental is not empty then it will be possible to view all the vehicles present.

[P]renota un mezzo
To book a vehicle, you must enter a valid date, a valid plate, i.e. a vehicle in the archive, and the name to which the booking is made. At this point the reservation will be inserted in the booking archive.

[M]ostra mezzi, per categoria, disponibili in una data a scelta
By entering a valid date and selecting the desired type of vehicle, a list of vehicles will be returned to the main, if they are not booked that day. If the archive does not contain vehicles of that category or if all the vehicles of that category are booked, then the program will return an error message.

[C]ancellazione della prenotazione
After checking that the reservation actually exists within the reservation archive, you can delete it. If the operation is successful, positive feedback will be returned to the main.

[I]ndicare il mezzo per visualizzare tutte le relative prenotazioni
Once the checks on the existence of the vehicle within the archive of the vehicles managed by the car rental and the presence of reservations of the same in the booking archive, these will be passed to the main who will print them.

[S]alvataggio su file
With this command you can save the changes, if they have been made.

[U]scita
Similar to the previous command, you can save the changes before exiting the program. In this case, however, the user will manage this option: in fact, you can also decide to leave the session without making any changes to the files.

# Description of classes and methods
In the following pages I will proceed by explaining all the classes, except the main one, and their respective methods.
Middle Class

Public Mezzo(String t, String m) throws IllegalArgumentException:
Manufacturer of the vehicle to which the license plate and model is passed, if the passed strings are empty an exception is thrown instead (managed in the main). The instance variables are then initialized in upper case.

Public String getTarga(): returns the plate in string.

Public String getModello(): returns the model of the medium in string.

Public String toString(): redefine the toString method, return the string with the license plate and model.

Public boolean equals(Object o): redefinition of the equals method: the comparison is based on the plate.

# Autovettura (Car) Class, subclass of the Middle Class

Public Autovettura (String t, String m, String seats, String power supply, String type) throws IllegalArgumentException: 
Manufacturer of the Car class, after checking that the seats are 2, 4 or 5, that the type of fuel is diesel or petrol, that the car is a small car, medium or sedan, the object is initialized. Otherwise an exception is launched (managed in the main).

Public String getNumPosti(): returns the number of seats.

Public String getAlimentazione(): returns the type of power supply in string format.

Public String getTipologiaAuto(): returns the typology of the car in string format.

Public String toString(): returns a string containing the information related to the plate, model, the number of places, the type of the power supply and the car.

# Furgone (Van) Class, Middle Class subclass

Public Furgone(String plate, String model, String pat, String d) throws IllegalArgumentException: 
Manufacturer of the van class. After verifying that the type of the license is B or C (regardless of whether it is written in lower or upper case) and that the size of the van is small, medium or large, the object is initialized. If some field does not pass the check, then an exception is launched (managed in the main).

Public String getPatente(): returns the license in string format. This choice was made to make it easier to handle input exceptions in the main.

Public String getDimensioni(): returns the size of the van in string format.

Public String toString(): redefinition of the toString method. It returns in string format the information relative to the plate, model, type of license and size of the van.

# Class Archivio (Archive) serves for the management of the vehicles

Public Archivio(String nomefile): 
Builder of the Archive class. The nomefile instance variables are initialized and modified. 
Also inside the constructor, recovering the value through the input file, the managed media vector is initialized with the information related to the saved media.

Public boolean aggiungiNuovoMezzo(Mezzo m): method that allows you to add a new medium to the managed media vector. 
First the vector is scanned, if the medium passed as argument corresponds to an element contained within the vector then it is returned false, otherwise the medium is added to the vector and the modified instance variable is set to true, so as to keep track of the changes made during the session and if it is necessary to overwrite the output file when closing the program or saving command.

Public boolean rimuoviMezzo(String targa): method used to remove a medium from the archive. Passing to the method the "targa" (plate), it is first verified the correspondence of the plate with that of some element of the managed media vector. If it actually exists, the position of this element in the vector is memorized and in a second time it is eliminated. As in the previous case, the modified instance variable is set to true.

Public Mezzo selezionaElemento(String t): method used to return a specific medium. In primis a "mezzo" (medium) is initialized to null, if then the plate passed as argument to the method finds correspondence in the vector meansManaged then the method returns to the main a medium otherwise returns null. 

Public Vector<Mezzo> elencoMezziDisposizione(): method that returns the managed Mezzo vector.

Public Vector <Mezzo> selezionaPerCategoria(String c): an empty vector of Mezzo is created, which will be subsequently returned to the main, this is then filled with the type of means (which is passed through the argument to the method): a selection is made in the vector managed Mezzo: if the list of cars is required the empty vector will be populated with the cars present in the archive; same thing for vans. If there are no vehicles of that type in managed vehicles, then it will be returned to the main null.

Public boolean daSalvare(): method that returns the value of the modified variable. So if you need to overwrite the output file.

Public boolean salva(): method that writes to the output file based on the value returned by the salva() method and resets the modified variable to false.

Public String toString(): Returns the managed Mezzo vector in string format.

# Reservation Class is used to create new reservations that will then be inserted in the ReservationArchive class.

Public Prenotazione(Mezzo m, String d, String n): 
Reservation Builder. The instance variables are the means, date and name to which the "Prenotazione" (reservation) is made.

Public Mezzo getMezzo(): Returns the medium.

Public Mezzo getDataPrenotazione(): Returns the date of the reservation.

Public Mezzo getNomePrenotazione(): Returns the name of the reservation.

Public boolean equals(Object o): redefines the equals method. The comparison is made on the basis of the medium and the date.

Public String toString(): ): redefinition of the toString method. Returns in string format the information related to the reservation: how the medium, who makes the reservation and for what date.

ArchivioPrenotazioni (ArchiveReservation) Class is used to manage reservations.

Public ArchivioPrenotazion(String nomefile): the nomefile instance variables are initialized and modified. Always inside the manufacturer, recovering the value through the input file, the list Reservations vector is initialized with the information related to the reservations recorded or that will be saved the first time the program is saved or closed.

Public boolean checkPrenotazione(Mezzo m, String data): method used to check that the vehicle is available on the requested date. Inside it, a reservation object is created (with an empty string for the name of the reservation holder), as the check is made on the date and the vehicle. The return value will be true if it has not been found correspondence of the reservation in question in the list Reservations carrier, otherwise false if the reservation has already been inserted.

Public Vector<Mezzo> getCategoriaData(String data, String category, Vector<Mezzo> vm): method that returns the list of means belonging to a certain category on a certain date. At first an empty vector is created, which will be filled with the type of means requested by the user. If the date passed as a parameter corresponds to that of an element in the Reservations list, and at the same time the plate of the medium of the same reservation coincides with that passed to the method, then the local reserved variable is set to true. At this point, a check is made on the type of the means passed as argument (these are the means managed by the archive. If the required category is car, then the cars that are not reserved are added to the return list. The same operation is done for vans. If the list is empty, then a null value is returned.

Public Prenotazione rimuoviPrenotazione(Mezzo m, String data): method that manages the removal of the reservation. Returns a positive value if the removal was successful, then if it found the reservation within the Reservations list and sets the modified variable to true. Otherwise it returns false, so if the carrier is empty or the requested reservation is not present in it.

Public Prenotazione visualizzaPrenotazione(Prenotazione p): method that returns the requested reservation, if it exists in the Reservations list, otherwise it returns a null value.

Public Vector<Prenotazione> cronologiaprenotazioniMezzo(String t): method that returns the list of all reservations for a single vehicle. Here is created a reservation vector that will then be returned to the main, this will then be populated, if the Reservations list is not empty, with the reservations that result to have the license plate of the vehicle corresponding to the one passed to the method. 

Public boolean daSalvare(): method that returns the value of the modified variable. So if you need to overwrite the output file.

Public boolean salva(): method that writes to the output file, based on the value returned by the Save() method and resets the modified variable to false.


