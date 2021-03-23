import random


def occupancy_check(floor):

    cap1 = 100
    cap2 = 80
    cap3 = 70

    if floor == 1:
        floor1 = random.randint(60, 120)
        if floor1 >= cap1:
            status = "Covid capacity breached - take action! > floor: " + str(floor) + " \nCurrent occupancy: " + str(floor1) + \
                     " \nCOVID specified capacity: " + str(cap1)
        elif cap1 - floor1 < 10:
            status = "Warning you are close to capacity for floor " + str(floor) + " \nCurrent occupancy: " + \
                     str(floor1) + " \nCOVID specified capacity: " + str(cap1)
        else:
            status = "Occucpancy levels compliant for floor " + str(floor) + " \nCurrent occupancy: " + \
                     str(floor1) + " \nCOVID specified capacity: " + str(cap1)
        print(status)
        return status
    elif floor == 2:
        floor2 = random.randint(40, 90)
        if floor2 >= cap2:
            status = "Covid capacity breached - take action! > floor: " + str(floor) + " \nCurrent occupancy: " + str(floor2) + \
                     " \nCOVID specified capacity: " + str(cap2)
        elif cap2 - floor2 < 10:
            status = "Warning you are close to capacity for floor " + str(floor) + " \nCurrent occupancy: " + \
                     str(floor2) + " \nCOVID specified capacity: " + str(cap2)
        else:
            status = "Occucpancy levels compliant for floor " + str(floor) + " \nCurrent occupancy: " + \
                     str(floor2) + " \nCOVID specified capacity: " + str(cap2)
        print(status)
        return status
    elif floor == 3:
        floor3 = random.randint(35, 85)
        if floor3 >= cap3:
            status = "Covid capacity breached - take action! > floor: " + str(floor) + " \nCurrent occupancy: " + str(floor3) + \
                     " \nCOVID specified capacity: " + str(cap3)
        elif cap3 - floor3 < 10:
            status = "Warning you are close to capacity for floor " + str(floor) + " \nCurrent occupancy: " + \
                     str(floor3) + " \nCOVID specified capacity: " + str(cap3)
        else:
            status = "Occucpancy levels compliant for floor " + str(floor) \
                     + " \nCurrent occupancy: " + str(floor3) \
                     + " \nCOVID specified capacity: " + str(cap3)
        print(status)
        return status
    else:
        status = "Please enter a valid floor number (1 - 3) "
        print(status)
        return status


# occupancy_check(3)
