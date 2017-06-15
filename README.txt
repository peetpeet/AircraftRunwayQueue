To run and see output on the console from the AC Queue implementation do the following:
1. Unzip the ACQueue.zip file contents into your Eclipse (or whichever IDE you use, see its import documentation) workspace.
2. Right-click on the Package Explorer panel and select Import from the popup menu
3. In the popup dialog select 'Existing Projects into Workspace' (the 1st choice)
4. In the next dialog Browse to the ACQueue directory (the top level folder in the zip archive that you previously unzipped in step 1 above)
5. Click Finish and you will see a new Java project called ACQueue in the Package Explorer panel.
6. Right-click the acqueue packege under the src folder and select Run As > Java Application
7. You should see output for 10 randomly created requests in the main() method of the class AircraftQueueManager. 
	- Follow the Enqueue and Dequeue patterns in the output to verify that the implementation complies with the expected output.
	- Run it a few times to test different scenarios, should not take more than a few runs to verify them.
	