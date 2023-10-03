<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Mark Goncalves (mag)</td></tr>
<tr><td> <em>Generated: </em> 10/2/2023 10:44:14 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-number-guesser/grade/mag" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><ol><li><a href="https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f">https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f</a><br></li></ol><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-03T02.21.12image.png.webp?alt=media&token=e3ee4a1c-9399-4dd6-a95e-2b801a82d5ab"/></td></tr>
<tr><td> <em>Caption:</em> <p>console <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-03T02.26.53image.png.webp?alt=media&token=a5a28a88-35f8-451d-a350-2e9d023ae1ef"/></td></tr>
<tr><td> <em>Caption:</em> <p>code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>For generating the hint I created a fixed range use min and max<br>to make sure the range is 3 numbers from the correct number.&nbsp; When<br>the hint is not used the hint will appear false and not be<br>sent but once two strikes are received the hint will be given. I<br>used and else if statement to make sure that the strikes were equal<br>to 2 and hint was not true.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-03T02.28.35image.png.webp?alt=media&token=3fa485da-528a-4a9f-b424-13c3d2217b12"/></td></tr>
<tr><td> <em>Caption:</em> <p>code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-03T02.36.11image.png.webp?alt=media&token=b9b5a0f1-fc7a-4bc1-b85e-6db4e44cbbdf"/></td></tr>
<tr><td> <em>Caption:</em> <p>console<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>This will see if the guess is correct, if it is not it<br>will check if the guess is a number that is lower than number.<br>If the correct number is higher than the guess it will print &quot;higher&quot;<br>and if the guess is a number higher than the correct number it<br>will print &quot;lower&quot;. This was done just use if and else statements.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/MarkaGon/mag-it114-001/pull/4">https://github.com/MarkaGon/mag-it114-001/pull/4</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>I mainly learned that using methods takes a lot of trial and error.<br>I had to go through and try to figure out how to correctly<br>set the range for giving the hint. It was quite frustrating but I<br>did get better at using math methods.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-number-guesser/grade/mag" target="_blank">Grading</a></td></tr></table>