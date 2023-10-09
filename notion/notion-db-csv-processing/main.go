package main

import (
	"encoding/csv"
	"fmt"
	"net/url"
	"os"
)

func main() {
	// Open the CSV file
	file, err := os.Open("data/uris-all.csv")
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	// Create a new CSV reader
	reader := csv.NewReader(file)

	// Read all records from the CSV file
	records, err := reader.ReadAll()
	if err != nil {
		fmt.Println("Error reading CSV file:", err)
		return
	}

	LINK_COLUMN_INDEX := 1
	KIND_COLUMN_INDEX := 3

	// Find the index of the "Link" column
	// linkColumnIndex := -1
	// header := records[0]
	// for i, columnName := range header {
	// 	if columnName == "Link" {
	// 		linkColumnIndex = i
	// 		break
	// 	}
	// }

	// if linkColumnIndex == -1 {
	// 	fmt.Println("Column 'Link' not found in CSV file")
	// 	return
	// }

	hostnames := make(map[string]int)

	// Print the values in the "Link" column
	for _, record := range records[1:] {

		// Only care about blog posts for now
		if record[KIND_COLUMN_INDEX] != "blog" {
			continue
		}
		link := record[LINK_COLUMN_INDEX]
		parsedURL, err := url.Parse(link)
		if err != nil {
			fmt.Println("Error parsing URL:", err)
			return
		}

		hostname := parsedURL.Hostname()

		if hostname == "" {
			continue
		}

		hostnames[hostname] += 1
	}

	for hostname, count := range hostnames {
		fmt.Println(count, hostname)
	}
}
