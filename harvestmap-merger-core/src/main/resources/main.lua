require("LibStub");
require("AceSerializer")

local AS = LibStub("AceSerializer")

argument  = ...

local result,data =AS:Deserialize(argument)

return (data[1] .. "," .. data[2] .. ",".. data[4] )

