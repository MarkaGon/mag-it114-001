<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone 2</td></tr>
<tr><td> <em>Student: </em> Mark Goncalves (mag)</td></tr>
<tr><td> <em>Generated: </em> 11/28/2023 1:38:40 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-chatroom-milestone-2/grade/mag" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp; <a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T16.46.30image.png.webp?alt=media&token=f8264b0e-f236-4c41-8050-7c9dcfb75054"/></td></tr>
<tr><td> <em>Caption:</em> <p>properties<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T16.47.01image.png.webp?alt=media&token=20df39b9-53d5-45b7-8316-69894ba72cc4"/></td></tr>
<tr><td> <em>Caption:</em> <p>toString<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T16.48.41image.png.webp?alt=media&token=3c2ced0b-2445-4a9d-9f7d-93017eb9b464"/></td></tr>
<tr><td> <em>Caption:</em> <p>toString in terminal<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Server-side commands </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the mentioned commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T17.11.24image.png.webp?alt=media&token=e1cf6ae1-771f-46db-bdb9-48da6eb6b14c"/></td></tr>
<tr><td> <em>Caption:</em> <p>roll and flip in terminal<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T16.56.52image.png.webp?alt=media&token=b83a454c-5179-4e5c-b2e9-c3aa9ae5492f"/></td></tr>
<tr><td> <em>Caption:</em> <p>flip code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T17.07.36image.png.webp?alt=media&token=839b0162-c9b7-4008-a471-39fc7300f039"/></td></tr>
<tr><td> <em>Caption:</em> <p>roll code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T17.10.23image.png.webp?alt=media&token=d06afb87-031c-407a-8705-99752e76b9f6"/></td></tr>
<tr><td> <em>Caption:</em> <p>process payload<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Explain the logic/implementation of each commands</td></tr>
<tr><td> <em>Response:</em> <p>How /flip work is that it does math to pick a random double<br>between 0.0 to 1.0 and if the value is greater than 0.5 it<br>will be tails if lower it will be heads. Result is then assigned<br>either head or strings depending on the value. Then we will print the<br>message to everyone in the current room.&nbsp;<br><br>For /roll I had done this in<br>a pervious lesson so I took from that last assignment. We first use<br>.split to separate the commands with a space. The commands takes two values<br>the number of dice rolled and the faces of those dice. the command<br>is separate with d so that you can put 1d6 for example. Insure<br>that the commands is split into two different values with using .length.&nbsp; Then<br>insure that the values for the command must be greater than 0. Then<br>we have the math for the rolling of dice&nbsp; and wrong inputs.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Text Display </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the various style handling via markdown or special characters</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T17.18.27image.png.webp?alt=media&token=4475b917-11c2-48ae-a03c-a7db72b23893"/></td></tr>
<tr><td> <em>Caption:</em> <p>code for html tags<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show source message and the result output in the terminal (note, you won't actually see the styles until Milestone3)</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T17.26.39image.png.webp?alt=media&token=c99118bc-2ad0-4929-be78-1978111834c4"/></td></tr>
<tr><td> <em>Caption:</em> <p>example in terminal of each alone<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-11-28T17.26.49image.png.webp?alt=media&token=7ffe9733-6e7c-4392-b39e-bcb17e76e708"/></td></tr>
<tr><td> <em>Caption:</em> <p>all together <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Explain how you got each style applied</td></tr>
<tr><td> <em>Response:</em> <div>The source of this code in the link in the screenshot roughly explains<br>using .replaceall and shows examples I copied a bit from it and then<br>looked up some of the common ways people implement it.&nbsp; The user will<br>type the pattern and then the replace all method will read that pattern<br>and replace the text with the HTML tags around it The color part<br>is the same idea but will type out the color bold, italics, underline.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/MarkaGon/mag-it114-001/pull/9">https://github.com/MarkaGon/mag-it114-001/pull/9</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-chatroom-milestone-2/grade/mag" target="_blank">Grading</a></td></tr></table>
