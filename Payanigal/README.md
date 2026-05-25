Common Features
1. Passenger account creation (Sign Up)

2. Different screens for different roles
      ADMIN     → Bus, Route
      PASSENGER → Search, Book, Cancel

3. Edit and Update Profile details
      (future enhancement)

4. Log In and Log Out

Admin Module
Bus Management

1. Add new Bus
      Bus Number, Operator Name, Bus Type, Total Seats

2. View all Buses
      ID, Bus Number, Operator, Type, Seats, Status

3. Bus Types available
      AC Sleeper
      AC Seater
      Non-AC Sleeper
      Non-AC Seater

4. Route Management


1. Add new Route
      Origin City, Destination City, Distance (km)

2. View all Routes
      ID, Origin, Destination, Distance

3. View all Cities
      Auto-collected from routes
      Shown to passenger before booking

Booking Management


1. View ALL bookings across all passengers
      PNR, Route, Date, Seats, Status, Fare, Passenger name


Reports


1. Summary Dashboard
      Total Users
      Total Buses
      Total Bookings (Confirmed + Cancelled)
      Total Revenue

2. Route Popularity Report
      Which route has most passengers


Passenger Module
Account


1. Sign Up
      Full Name
      Email (validated)
      Password (min 8 chars, letters + digits)
      Mobile Number (10 digits, starts 6-9)
      Date of Birth (must be 18+)

2. Sign In
      Email + Password
      Wrong password → error shown
      Inactive account → blocked

3. Sign Out
      Returns to landing menu


Search


1. Search Buses
      Enter Origin City
      Enter Destination City
      Enter Travel Date

2. Search Results show
      Operator Name
      Bus Type
      Fare per Seat
      Seats Available


Booking


1. Book a Ticket
      Search buses first
      Select bus from results
      Choose number of seats
      Select seat numbers one by one
      View Booking Summary
      Confirm → PNR generated

2. Booking Summary shows
      Route
      Date
      Bus name and type
      Selected seats
      Total Fare


My Bookings


1. View My Bookings
      All bookings listed
      PNR, Route, Date, Seats, Fare, Status

2. View Booking Details / PNR Status
      Enter PNR number
      Full details shown
            Route
            Date
            Bus details
            Seats
            Total Fare
            Booking time
            Cancellation time (if cancelled)


Cancellation


1. Cancel Booking
      Shows only FUTURE CONFIRMED bookings
      Select booking from list
      Shows booking summary
      Ask for confirmation (Y/N)
      Enter cancellation reason
      Booking marked CANCELLED
      Seats released back for others
      Refund notification sent

