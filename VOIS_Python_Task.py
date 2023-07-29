import csv
import statistics

def Open_and_Read_csv(path):
    with open(path, 'r') as file:
        csvreader = csv.reader(file)
        FileData = list(csvreader)
    return FileData


def Remove_Duplicates(DataList):
    NewDataList = []
    for i in DataList:
        if i in NewDataList:
            continue
        else:
            NewDataList.append(i)
    NewDataList[0][0] = 'Name'
    return NewDataList


def Remove_Decimals_From_Age(DataList):
    for i in DataList[1:]:
        i[2] = i[2].split('.')[0]
    return DataList

def Convert_Salary_to_EGP(DataList, CurrencyRate):
    for i in DataList[1:]:
        New_Salary = int(i[3]) * CurrencyRate
        i[3] = str(New_Salary)
    DataList[0][3] = 'Salary(EGP)'
    return DataList

def Print_Stats(DataList):
    AgeSum = 0
    Salaries = []
    Males = 0
    Females = 0
    for i in DataList[1:]:
        AgeSum += int(i[2])
        Salaries.append(int(i[3]))
        if i[1] == 'M':
            Males += 1
        elif i[1] == 'F':
            Females += 1
    Average = AgeSum/(len(DataList)-1)
    Median = statistics.median(Salaries)
    Ratio = Males/Females
    print("Average Age = " , Average)
    print("Salaries Median = " , Median)
    print("Males to Females ratio: = " , Ratio)

def View_List(DataList):
    print("Employees Table:")
    for i in DataList:
        print(i)

def Write_to_csv(Datalist, csv_file_name):
    with open(csv_file_name+'.csv', 'w') as file:
        write = csv.writer(file)
        write.writerow(Datalist[0])
        write.writerows(Datalist[1:])


# Open csv file and return its data in list
NewDataList = Open_and_Read_csv("./Employees.csv")

# Remove duplicates from table and return the list without duplicates
NewDataList = Remove_Duplicates(NewDataList)

# Remove decimals from Age field in table and return the list without decimals
NewDataList = Remove_Decimals_From_Age(NewDataList)

# Convert USD Salaries to EGP and save to table then return the list
NewDataList = Convert_Salary_to_EGP(NewDataList ,31)

# Print different Stats
Print_Stats(NewDataList)

# View List Components
View_List(NewDataList)

# Save new table after modification to csv file with configurable file name
Write_to_csv(NewDataList, 'Employees_New')