<table><tr><td> <em>Assignment: </em> It114 Milestone1</td></tr>
<tr><td> <em>Student: </em> Mark Goncalves (mag)</td></tr>
<tr><td> <em>Generated: </em> 10/23/2023 8:01:26 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-milestone1/grade/mag" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create a new branch called Milestone1</li><li>At the root of your repository create a folder called Project if one doesn't exist yet</li><ol><li>You will be updating this folder with new code as you do milestones</li><li>You won't be creating separate folders for milestones; milestones are just branches</li></ol><li>Create a milestone1.md file inside the Project folder</li><li>Git add/commit/push it to Github (yes it'll be blank for now)</li><li>Create a pull request from Milestone1 to main (don't complete/merge it yet, just have it in open status)</li><li>Copy in the latest Socket sample code from the most recent Socket Part example of the lessons</li><ol><li>Recommended Part 5 (clients should be having names at this point and not ids)</li><li><a href="https://github.com/MattToegel/IT114/tree/Module5/Module5">https://github.com/MattToegel/IT114/tree/Module5/Module5</a>&nbsp;<br></li></ol><li>Fix the package references at the top of each file (these are the only edits you should do at this point)</li><li>Git add/commit the baseline</li><li>Ensure the sample is working and fill in the below deliverables</li><ol><li>Note: The client commands likely are different in part 5 with the /name and /connect options instead of just connect</li></ol><li>Get the markdown content or the file and paste it into the milestone1.md file or replace the file with the downloaded version</li><li>Git add/commit/push all changes</li><li>Complete the pull request merge from step 5</li><li>Locally checkout main</li><li>git pull origin main</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Startup </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot showing your server being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-23T22.41.18image.png.webp?alt=media&token=6c3a66a2-8aed-4cfd-bd7f-732d159bd0cb"/></td></tr>
<tr><td> <em>Caption:</em> <p>Server started and running<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot showing your client being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-23T22.40.40image.png.webp?alt=media&token=af1a6455-ead7-4e5c-b2a2-d9d2da747464"/></td></tr>
<tr><td> <em>Caption:</em> <p>client connected to server<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the connection process</td></tr>
<tr><td> <em>Response:</em> <p>The server has a socket that has a predetermined port and is waiting<br>for a client to connect. When the client connects by inputting the port,<br>this is a local connection. Seversocket has the port number and listens for<br>the connection of clients. A socket will be created when the client connects<br>to the server.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Sending/Receiving </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-23T22.46.35image.png.webp?alt=media&token=be495679-11ba-468b-bed6-824fdb495110"/></td></tr>
<tr><td> <em>Caption:</em> <p>two client communicating<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-23T22.54.46image.png.webp?alt=media&token=d9cbbf26-5079-47c7-9f7b-d08baca2a054"/></td></tr>
<tr><td> <em>Caption:</em> <p>clients in different rooms<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the messages are sent, broadcasted (sent to all connected clients), and received</td></tr>
<tr><td> <em>Response:</em> <p>Messages are read as a payload and then taken by the server, determining<br>what they do and what the client is trying to do. The serverthread<br>sends the message to each client using the payload and passing it to<br>others. The room will send a message to everyone in the room by<br>using the payload, which checks what kind of message it is. If the<br>message is valid, the room will send a message to everyone in the<br>room. Finally, when a client gets the message, the message is taken as<br>a payload, and the type will be a message that will show who<br>is sending what and the text they sent.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Disconnecting / Terminating </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-23T22.57.58image.png.webp?alt=media&token=32052d9e-5e53-4775-8233-35ca39e6f9c2"/></td></tr>
<tr><td> <em>Caption:</em> <p>client disconnect <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-10-23T23.00.43image.png.webp?alt=media&token=5d86379b-2683-4d0d-90f1-8460fd822a8c"/></td></tr>
<tr><td> <em>Caption:</em> <p>server terminating and clients able to reconnect<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the various disconnects/terminations are handled</td></tr>
<tr><td> <em>Response:</em> <p>When the client types or enters a command to disconnect, it will close<br>the socket connection. When the server disconnects/terminates, the client program continues to run<br>because the serverthread handles the disconnect from the server. When another server is<br>available, the client needs to connect again. The server will also continue running<br>when a client disconnects because it is managed but a different serverthread, so<br>it does not affect the server&#39;s connection to other clients.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add the pull request for this branch</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/MarkaGon/mag-it114-001/pull/7">https://github.com/MarkaGon/mag-it114-001/pull/7</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Talk about any issues or learnings during this assignment</td></tr>
<tr><td> <em>Response:</em> <p>There was a lot of information during the learning part of this assignment,<br>as we went over many things in class, but regardless, it is a<br>lot to digest.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-milestone1/grade/mag" target="_blank">Grading</a></td></tr></table>