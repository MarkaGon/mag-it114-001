<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone4</td></tr>
<tr><td> <em>Student: </em> Mark Goncalves (mag)</td></tr>
<tr><td> <em>Generated: </em> 12/13/2023 8:31:54 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-chatroom-milestone4/grade/mag" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Client can export chat history of their current session (client-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot of related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T20.50.21image.png.webp?alt=media&token=77fb913e-717c-461b-a318-54a51dbee880"/></td></tr>
<tr><td> <em>Caption:</em> <p>export chat history button<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot of exported data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T20.55.43image.png.webp?alt=media&token=c0ab5260-e972-4379-95a0-c038f8ee2979"/></td></tr>
<tr><td> <em>Caption:</em> <p>chat history gets exported<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>All of the implementation was done in the ChatPanel. First made a list<br>for the chat history, then make a new button using JButton. When the<br>button is pressed it will use the exportchathistory and go through the list<br>for chat history. Exportchathistory make a chatHistory.txt file when used which goes through<br>the list writes it into the file.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client's mute list will persist across sessions (server-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot of how the mute list is stored</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T21.18.40image.png.webp?alt=media&token=5f37522f-467a-4ecf-9336-a462ca74dbdd"/></td></tr>
<tr><td> <em>Caption:</em> <p>muteList.txt<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T21.23.58image.png.webp?alt=media&token=f6f62df4-96e5-4d26-b7e9-08b61e4f0f91"/></td></tr>
<tr><td> <em>Caption:</em> <p>creating the muteList.txt<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the code saving/loading mute list</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T21.24.53image.png.webp?alt=media&token=969132cf-0c69-420a-93e3-8b44a4092955"/></td></tr>
<tr><td> <em>Caption:</em> <p>saving and loading<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T21.26.18image.png.webp?alt=media&token=644ef5f3-6aed-458a-9f06-d244a05db536"/></td></tr>
<tr><td> <em>Caption:</em> <p>saving to the muteList<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>first we need to make saveMuteListToFile which saves a list of the users<br>in mutedUser and adds them into muteList.txt. Then make loadMuteListToFile which will load<br>the previous muteList.txt. Then we use saveMuteListToFile in muteUser and in unmuteUser. This<br>will insure that people are added or remove from the text file.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Client's will receive a message when they get muted/unmuted by another user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot showing the related chat messages</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T21.33.37image.png.webp?alt=media&token=ff064993-afb2-4f3c-8d8b-51c44f0344cd"/></td></tr>
<tr><td> <em>Caption:</em> <p>mute/unmute state users names<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the related code snippets</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-13T21.28.37image.png.webp?alt=media&token=3d4912da-495f-48b3-aa38-168acbff9cd9"/></td></tr>
<tr><td> <em>Caption:</em> <p>related code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>All that needed to be done for this implementation is in the message<br>sent to the person getting muted or unmuted to include &quot;client.getclientname()&quot; and when<br>sending a message to the person muting or unmuting someone to include &quot;user&quot;<br>and &quot;username&quot;.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> User list should update per the status of each user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot for Muted users by the client should appear grayed out</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-14T00.32.42image.png.webp?alt=media&token=c93c6bf0-e8c3-41c0-8c00-5c9e410bff23"/></td></tr>
<tr><td> <em>Caption:</em> <p>couldnt figure it out<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot for Last person to send a message gets highlighted</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fmag%2F2023-12-14T00.32.46image.png.webp?alt=media&token=4d3681ca-3a4d-4e76-a694-fe5bc9ef232c"/></td></tr>
<tr><td> <em>Caption:</em> <p>couldnt figure it out<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>My trouble mainly was found when trying to gray the background. All the<br>changes I made to the userlistpanel would cutoff the names in the list.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-chatroom-milestone4/grade/mag" target="_blank">Grading</a></td></tr></table>